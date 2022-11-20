package com.ssafy.foodtruck.model.service;

import com.ssafy.foodtruck.constant.OrdersErrorMessage;
import com.ssafy.foodtruck.db.entity.*;
import com.ssafy.foodtruck.db.repository.*;
import com.ssafy.foodtruck.dto.request.AcceptOrdersReq;
import com.ssafy.foodtruck.dto.request.OrdersMenuReq;
import com.ssafy.foodtruck.dto.request.RegisterOrdersReq;
import com.ssafy.foodtruck.dto.response.*;
import com.ssafy.foodtruck.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

import static com.ssafy.foodtruck.constant.OrdersConsatnt.NOT_FOUND_ORDER_ERROR_MESSAGE;

@RequiredArgsConstructor
@Service
public class OrdersService {

	private final OrdersRepository ordersRepository;
	private final OrdersMenuRepository ordersMenuRepository;
	private final MenuRepository menuRepository;
	private final UserRepository userRepository;
	private final FoodtruckRepository foodTruckRepository;
	private final ReviewRepository reviewRepository;


	// 주문 내역을 등록하고, 카카오 페이 요청 시 필요한 데이터를 리턴
	@Transactional
	public RegisterOrdersRes registerOrders(RegisterOrdersReq registerOrdersReq, User user) {
		FoodTruck foodTruck = foodTruckRepository.findById(registerOrdersReq.getFoodtruckId())
			.orElseThrow(() -> new NotFoundException(OrdersErrorMessage.NOT_FOUND_FOODTRUCK));

		final Orders orders = Orders.builder()
			.user(user)
			.foodTruck(foodTruck)
			.build();
		Orders savedOrders = ordersRepository.save(orders);

		List<OrdersMenuReq> menuList = registerOrdersReq.getMenuList();
		String payMenuName = "";
		Integer totalQuantity = 0;
		Integer totalAmount = 0;
		for(OrdersMenuReq menuReq : menuList){
			Menu menu = menuRepository.findById(menuReq.getMenuId())
				.orElseThrow(() -> new NotFoundException(OrdersErrorMessage.NOT_FOUND_MENU));

			ordersMenuRepository.save(OrdersMenu.builder()
				.orders(savedOrders)
				.menu(menu)
				.count(menuReq.getCount())
				.build());

			if(payMenuName == "")
				payMenuName = menu.getName()+" 외 " + (menuList.size()-1) + "개";
			totalQuantity += menuReq.getCount();
			totalAmount += menu.getPrice();
		}

		// 카카오페이에 필요한 데이터
		return RegisterOrdersRes.builder()
			.orders(savedOrders)
			.payMenuName(payMenuName)
			.totalQuantity(totalQuantity)
			.totalAmount(totalAmount)
			.build();
	}

	// 카카오페이 결제 완료 건 is_paied true 로 변경
	@Transactional
	public void successPay(Integer ordersId){
		Orders orders = ordersRepository.findById(ordersId)
			.orElseThrow(() -> new NotFoundException(OrdersErrorMessage.NOT_FOUND_ORDER));
		orders.setIsPaid(true);
	}


	@Transactional
	public void acceptOrders(int ceoId, AcceptOrdersReq acceptOrdersReq) {
		Orders orders = ordersRepository.findById(acceptOrdersReq.getOrdersId())
			.orElseThrow(() -> new NotFoundException(OrdersErrorMessage.NOT_FOUND_MENU));

		// 주문이 사장님 아이디인지 확인
		if (ceoId != orders.getFoodTruck().getUser().getId()) {
			throw new NotFoundException(OrdersErrorMessage.NOT_FOUND_USER);
		}
		orders.setIsAccepted(true, LocalDateTime.now().plusMinutes(acceptOrdersReq.getDoneDate()));
	}

	public List<CurrentOrdersHistoryRes> getCustomerOrders(int customerId) {
		List<Orders> ordersList = ordersRepository.findCustomerOrders(customerId);

		List<CurrentOrdersHistoryRes> currentOrdersHistoryResList = new ArrayList<>();

		for(Orders orders : ordersList){
			List<OrdersMenu> ordersMenuList = ordersMenuRepository.findAllByOrders(orders);
			List<GetOrdersMenuRes> menuList = new ArrayList<>();

			for(OrdersMenu ordersMenu : ordersMenuList){
				menuList.add(GetOrdersMenuRes.of(ordersMenu.getMenu().getName(), ordersMenu.getCount(), ordersMenu.getMenu().getMenuImg()));
			}
			currentOrdersHistoryResList.add(
				CurrentOrdersHistoryRes.of(orders.getId(), orders.getFoodTruck().getName(), orders.getFoodTruck().getFoodtruckImg(), orders.getRegDate(), menuList));
			;
		}

		return currentOrdersHistoryResList;
	}

	public List<OrdersHistoryAllRes> getCustomerOrdersAll(User user) {

		List<OrdersHistoryAllRes> ordersHistoryAllResList = new ArrayList<>();
		List<OrdersHistoryRes> ordersHistoryResList = new ArrayList<>();
		List<Orders> ordersList = ordersRepository.findAllByUser(user.getId());

		if(ordersList.size() == 0) {
			throw new NoSuchElementException(NOT_FOUND_ORDER_ERROR_MESSAGE);
		}

		LocalDateTime baseOrderDate = ordersList.get(0).getRegDate();
		String baseDayOfWeek = baseOrderDate.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREA);
		String baseDateStr = baseOrderDate.getYear()+"/"+baseOrderDate.getMonthValue()+"/"+baseOrderDate.getDayOfMonth()+"("+baseDayOfWeek+")";

		for(int i=0; i<ordersList.size(); i++) {
			Orders orders = ordersList.get(i);

			LocalDateTime date = orders.getRegDate();
			String dayOfWeek = date.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREA);
			String dateStr = date.getYear()+"/"+date.getMonthValue()+"/"+date.getDayOfMonth()+"("+dayOfWeek+")";

			String menuDescription="";
			List<OrdersMenu> ordersMenuList = ordersMenuRepository.findAllByOrders(orders);
			for(int j=0; j<ordersMenuList.size(); j++){
				OrdersMenu ordersMenu = ordersMenuList.get(j);
				menuDescription += ordersMenu.getMenu().getName() + " " + ordersMenu.getCount() + "개";
				if(j != ordersMenuList.size()-1) {
					menuDescription += ", ";
				}
			}

			boolean isReviewed = false;
			Review review = reviewRepository.findReviewByOrdersAndUser(orders, user).orElse(null);
			if(review != null) isReviewed = true;

			if(baseDateStr.equals(dateStr)) {
				ordersHistoryResList.add(OrdersHistoryRes.of(orders, isReviewed, menuDescription));
				baseDateStr = dateStr;
			} else {
				// 날짜가 달라지면 이전 날짜 가져와서 add
				List<OrdersHistoryRes> input = new ArrayList<>();
				input.addAll(ordersHistoryResList);

				ordersHistoryAllResList.add(OrdersHistoryAllRes.builder()
					.orderDate(baseDateStr)
					.ordersHistoryResList(input).build());

				ordersHistoryResList.clear();

				// 주문 내역 list 에 현재 주문 내역 add
				ordersHistoryResList.add(OrdersHistoryRes.of(orders, isReviewed, menuDescription));
				baseDateStr = dateStr;
			}

			if(i==ordersList.size()-1) {	// 마지막 주문 내역
				ordersHistoryAllResList.add(OrdersHistoryAllRes.builder()
					.orderDate(baseDateStr)
					.ordersHistoryResList(ordersHistoryResList).build());
			}
		}

		return ordersHistoryAllResList;
	}

	public List<CurrentOrdersListByFoodtruckRes> getCeoOrdersNotAccepted(User ceoUser) {
		List<CurrentOrdersListByFoodtruckRes> currentOrdersListByFoodtruckResponseList = new ArrayList<>();

		FoodTruck foodTruck = foodTruckRepository.findByUser(ceoUser)
			.orElseThrow(() -> new NotFoundException(OrdersErrorMessage.NOT_FOUND_FOODTRUCK));

		List<Orders> ordersList = ordersRepository.findCeoOrdersNotAccepted(foodTruck.getId());

		for(Orders orders : ordersList){
			List<GetOrdersMenuRes> menuResList = new ArrayList<>();
			List<OrdersMenu> ordersMenuList = ordersMenuRepository.findAllByOrders(orders);
			for(OrdersMenu ordersMenu : ordersMenuList){
				menuResList.add(GetOrdersMenuRes.builder()
					.menuName(ordersMenu.getMenu().getName())
					.count(ordersMenu.getCount())
					.build());
			}
			currentOrdersListByFoodtruckResponseList.add(CurrentOrdersListByFoodtruckRes.builder()
				.ordersId(orders.getId())
				.orderUserId(orders.getUser().getId())
				.isAccepted(orders.getIsAccepted())
				.acceptTime(orders.getRegDate())
				.menuResList(menuResList).build());
		}

		return currentOrdersListByFoodtruckResponseList;
	}

	public List<CurrentOrdersListByFoodtruckRes> getCeoOrdersAccepted(User ceoUser) {
		List<CurrentOrdersListByFoodtruckRes> currentOrdersListByFoodtruckResponseList = new ArrayList<>();

		FoodTruck foodTruck = foodTruckRepository.findByUser(ceoUser)
			.orElseThrow(() -> new NotFoundException(OrdersErrorMessage.NOT_FOUND_FOODTRUCK));
		List<Orders> ordersList = ordersRepository.findCeoOrdersAccepted(foodTruck.getId());

		for(Orders orders : ordersList){
			List<GetOrdersMenuRes> menuResList = new ArrayList<>();
			List<OrdersMenu> ordersMenuList = ordersMenuRepository.findAllByOrders(orders);
			for(OrdersMenu ordersMenu : ordersMenuList){
				menuResList.add(GetOrdersMenuRes.builder()
					.menuName(ordersMenu.getMenu().getName())
					.count(ordersMenu.getCount())
					.build());
			}
			currentOrdersListByFoodtruckResponseList.add(CurrentOrdersListByFoodtruckRes.builder()
				.ordersId(orders.getId())
				.orderUserId(orders.getUser().getId())
				.isAccepted(orders.getIsAccepted())
				.acceptTime(orders.getRegDate())
				.menuResList(menuResList).build());
		}

		return currentOrdersListByFoodtruckResponseList;
	}

//	public List<OrdersListByFoodtruckRes> getCeoOrdersAll(int ceoId) {
//
//		// 사장님 찾기
//		User user = userRepository.findById(ceoId)
//			.orElseThrow(() -> new NotFoundException(OrdersErrorMessage.NOT_FOUND_USER));
//
//		// 사장님 푸드트럭
//		FoodTruck foodTruck = foodTruckRepository.findByUser(user)
//			.orElseThrow(() -> new NotFoundException(OrdersErrorMessage.NOT_FOUND_FOODTRUCK));
//
//		List<Orders> ordersList = ordersRepository.findByCeoOrdersAll(foodTruck.getId());
//		List<OrdersListByFoodtruckRes> ordersListByFoodtruckResponseList = new ArrayList<>();
//
//		for(Orders order : ordersList){
//			ordersListByFoodtruckResponseList.add(OrdersListByFoodtruckRes.builder().build());
//		}
//
//		return ordersListByFoodtruckResponseList;
//	}

	@Transactional
	public void cancelOrders(int ceoId, int orderId) {
		Orders orders = ordersRepository.findById(orderId)
			.orElseThrow(() -> new NotFoundException(OrdersErrorMessage.NOT_FOUND_MENU));

		if (ceoId != orders.getFoodTruck().getUser().getId()) {
			throw new NotFoundException(OrdersErrorMessage.NOT_FOUND_USER);
		}

		orders.setIsCanceled(true);
	}

	@Transactional
	public void doneOrders(int ceoId, int orderId) {
		Orders orders = ordersRepository.findById(orderId)
		.orElseThrow(() -> new NotFoundException(OrdersErrorMessage.NOT_FOUND_MENU));

		if (ceoId != orders.getFoodTruck().getUser().getId()) {
			throw new NotFoundException(OrdersErrorMessage.NOT_FOUND_USER);
		}
		orders.setIsDone(true);
	}
}
