package com.ssafy.foodtruck.model.service;

import com.ssafy.foodtruck.constant.OrdersErrorMessage;
import com.ssafy.foodtruck.db.entity.*;
import com.ssafy.foodtruck.db.repository.*;
import com.ssafy.foodtruck.dto.request.AcceptOrdersReq;
import com.ssafy.foodtruck.dto.request.RegisterMenuReq;
import com.ssafy.foodtruck.dto.request.RegisterOrdersReq;
import com.ssafy.foodtruck.dto.response.*;
import com.ssafy.foodtruck.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrdersService {

	private final OrdersRepository ordersRepository;
	private final OrdersMenuRepository ordersMenuRepository;
	private final MenuRepository menuRepository;
	private final UserRepository userRepository;
	private final FoodtruckRepository foodTruckRepository;
	private final ReviewRepository reviewRepository;

	@Transactional
	public void registerOrders(RegisterOrdersReq registerOrdersReq, User user) {
		FoodTruck foodTruck = foodTruckRepository.findById(registerOrdersReq.getFoodtruckId())
			.orElseThrow(() -> new NotFoundException(OrdersErrorMessage.NOT_FOUND_FOODTRUCK));
		final Orders orders = Orders.builder()
			.user(user)
			.foodTruck(foodTruck)
			.build();
		Orders savedOrders = ordersRepository.save(orders);

		List<RegisterMenuReq> menuList = registerOrdersReq.getMenuList();
		for(RegisterMenuReq menuReq : menuList){
			Menu menu = menuRepository.findById(menuReq.getMenuId())
				.orElseThrow(() -> new NotFoundException(OrdersErrorMessage.NOT_FOUND_MENU));
			ordersMenuRepository.save(OrdersMenu.builder()
				.orders(savedOrders)
				.menu(menu)
				.count(menuReq.getCount())
				.build());
		}
	}

	@Transactional
	public void acceptOrders(int ceoId, AcceptOrdersReq acceptOrdersReq) {
		Orders orders = ordersRepository.findById(acceptOrdersReq.getOrdersId())
			.orElseThrow(() -> new NotFoundException(OrdersErrorMessage.NOT_FOUND_MENU));

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
				menuList.add(GetOrdersMenuRes.builder()
					.menuName(ordersMenu.getMenu().getName())
					.count(ordersMenu.getCount())
					.build());
			}
			currentOrdersHistoryResList.add(
				CurrentOrdersHistoryRes.builder()
					.ordersId(orders.getId())
					.foodtruckName(orders.getFoodTruck().getName())
					.acceptTime(orders.getDoneDate())
					.menuList(menuList).build()
			);
		}


//		int count = ordersRepository.findByCount(customerId);
//		for (Orders orders : ordersList) {
//			OrdersMenu ordersMenu = ordersMenuRepository.findById(orders.getId())
//				.orElseThrow(() -> new NotFoundException(OrdersErrorMessage.NOT_FOUND_MENU));
//			currentOrdersHistoryResList.add(
//				CurrentOrdersHistoryRes.builder()
//					.foodtruckName(orders.getFoodTruck().getName())
//					.menuName(ordersMenu.getMenu().getName())
//					.acceptTime(orders.getRegDate())
//					.count(count)
//					.build());
//		}
		return currentOrdersHistoryResList;
	}

	public List<OrdersHistoryRes> getCustomerOrdersAll(User user) {
		List<OrdersHistoryRes> ordersHistoryResList = new ArrayList<>();
		List<Orders> ordersList = ordersRepository.findAllByUser(user.getId());

		for(Orders order : ordersList){
			List<GetOrdersMenuRes> menuResList = new ArrayList<>();
			List<OrdersMenu> ordersMenuList = ordersMenuRepository.findAllByOrders(order);
			for(OrdersMenu ordersMenu : ordersMenuList){
				menuResList.add(GetOrdersMenuRes.builder()
					.menuName(ordersMenu.getMenu().getName())
					.count(ordersMenu.getCount()).build());
			}
			boolean isReviewed = false;
			Review review = reviewRepository.findReviewByOrdersAndUser(order, user).orElse(null);
			if(review != null) isReviewed = true;
			ordersHistoryResList.add(OrdersHistoryRes.of(order, isReviewed, menuResList));
		}

//		List<Orders> ordersList = ordersRepository.findByCustomerOrdersAll(customerId);
//		List<OrdersHistoryRes> ordersHistoryResList = new ArrayList<>();

//		for (Orders orders : ordersList) {
//			List<OrdersMenu> ordersMenuList = ordersMenuRepository.findAllByOrders(orders);
//			ordersHistoryResList.add(
//				OrdersHistoryRes.builder()
//					.foodtruckName(orders.getFoodTruck().getName())
//					.menuName(ordersMenu.getMenu().getName())
//					.acceptTime(orders.getRegDate())
//					.build());
//		}
		return ordersHistoryResList;
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

//		User user = userRepository.findById(ceoId)
//			.orElseThrow(() -> new NotFoundException(OrdersErrorMessage.NOT_FOUND_USER));
//		FoodTruck foodTruck = foodTruckRepository.findByUser(user)
//			.orElseThrow(() -> new NotFoundException(OrdersErrorMessage.NOT_FOUND_FOODTRUCK));
//		List<Orders> ordersList = ordersRepository.findByCeoOrders(foodTruck.getId());
//		List<CurrentOrdersListByFoodtruckRes> currentOrdersListByFoodtruckResponseList = new ArrayList<>();

//		for (Orders orders : ordersList) {
//			OrdersMenu ordersMenu = ordersMenuRepository.findByOrdersId(orders.getId());
//			currentOrdersListByFoodtruckResponseList.add(
//				CurrentOrdersListByFoodtruckRes.builder()
//					.foodtruckName(orders.getFoodTruck().getName())
//					.menuName(ordersMenu.getMenu().getName())
//					.acceptTime(orders.getRegDate())
//					.build());
//		}
//		return currentOrdersListByFoodtruckResponseList;
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

	public List<OrdersListByFoodtruckRes> getCeoOrdersAll(int ceoId) {
		User user = userRepository.findById(ceoId)
			.orElseThrow(() -> new NotFoundException(OrdersErrorMessage.NOT_FOUND_USER));
		FoodTruck foodTruck = foodTruckRepository.findByUser(user)
			.orElseThrow(() -> new NotFoundException(OrdersErrorMessage.NOT_FOUND_FOODTRUCK));
		List<Orders> ordersList = ordersRepository.findByCeoOrdersAll(foodTruck.getId());
		List<OrdersListByFoodtruckRes> ordersListByFoodtruckResponseList = new ArrayList<>();

//		for (Orders orders : ordersList) {
//			OrdersMenu ordersMenu = ordersMenuRepository.findByOrdersId(orders.getId());
//
//			ordersListByFoodtruckResponseList.add(
//				OrdersListByFoodtruckRes.builder()
//					.foodtruckName(orders.getFoodTruck().getName())
//					.menuName(ordersMenu.getMenu().getName())
//					.acceptTime(orders.getRegDate())
//					.build());
//		}
		return ordersListByFoodtruckResponseList;
	}

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
