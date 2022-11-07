package com.ssafy.foodtruck.model.service;

import com.ssafy.foodtruck.db.entity.*;
import com.ssafy.foodtruck.db.repository.*;
import com.ssafy.foodtruck.dto.*;
import com.ssafy.foodtruck.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrdersService {

	private final OrdersRepository ordersRepository;
	private final OrdersMenuRepository ordersMenuRepository;

	private final MenuRepository menuRepository;

	private final UserRepository userRepository;
	private final FoodTruckRepository foodTruckRepository;

	@Transactional
	public void registerOrders(int customerId, List<RegisterOrdersReq> registerOrdersReqList) {

		User user = userRepository.findById(customerId).get();

		for(RegisterOrdersReq registerOrdersReq : registerOrdersReqList){

			FoodTruck foodTruck = foodTruckRepository.findById(registerOrdersReq.getFoodtruckId()).get();

			Orders orders = Orders.builder()
				.user(user)
				.foodTruck(foodTruck)
				.build();

			ordersRepository.save(orders);

			Menu menu = menuRepository.findById(registerOrdersReq.getMenuId()).get();

			OrdersMenu ordersMenu = OrdersMenu.builder()
				.orders(orders)
				.menu(menu)
				.build();

			ordersMenuRepository.save(ordersMenu);
		}
	}

	@Transactional
	public void acceptOrders(int ceoId, int ordersId) {
		Orders orders = ordersRepository.findById(ordersId)
			.orElseThrow(() -> new NotFoundException(OrdersErrorMessage.NOT_FOUND_MENU));

		if(ceoId != orders.getFoodTruck().getUser().getId()){
			throw new NotFoundException(OrdersErrorMessage.NOT_FOUND_USER);
		}

		orders.setIsAccepted(true);
	}

	public List<CurrentOrdersHistoryResponse> getCustomerOrders(int customerId) {
		List<Orders> ordersList = ordersRepository.findByCustomerOrders(customerId);

		List<CurrentOrdersHistoryResponse> currentOrdersHistoryResponseList = new ArrayList<>();

		for(Orders orders : ordersList) {

			OrdersMenu ordersMenu = ordersMenuRepository.findByOrdersId(orders.getId());

			currentOrdersHistoryResponseList.add(
				CurrentOrdersHistoryResponse.builder()
					.foodtruckName(orders.getFoodTruck().getName())
					.menuName(ordersMenu.getMenu().getName())
					.build());
		}

		return currentOrdersHistoryResponseList;
	}

	public List<OrdersHistoryResponse> getCustomerOrdersAll(int customerId) {
		List<Orders> ordersList = ordersRepository.findByCustomerOrdersAll(customerId);
		List<OrdersHistoryResponse> OrdersHistoryResponseList = new ArrayList<>();

		for(Orders orders : ordersList) {

			OrdersMenu ordersMenu = ordersMenuRepository.findByOrdersId(orders.getId());

			OrdersHistoryResponseList.add(
				OrdersHistoryResponse.builder()
					.foodtruckName(orders.getFoodTruck().getName())
					.menuName(ordersMenu.getMenu().getName())
					.build());
		}

		return OrdersHistoryResponseList;
	}

	public List<CurrentOrdersListByFoodtruckResponse> getCeoOrders(int ceoId) {
		User user = userRepository.findById(ceoId).get();
		FoodTruck foodTruck = foodTruckRepository.findByUser(user).get();

		List<Orders> ordersList = ordersRepository.findByCeoOrders(foodTruck.getId());

		List<CurrentOrdersListByFoodtruckResponse> currentOrdersListByFoodtruckResponseList = new ArrayList<>();

		for (Orders orders : ordersList) {

			OrdersMenu ordersMenu = ordersMenuRepository.findByOrdersId(orders.getId());

			currentOrdersListByFoodtruckResponseList.add(
				CurrentOrdersListByFoodtruckResponse.builder()
					.foodtruckName(orders.getFoodTruck().getName())
					.menuName(ordersMenu.getMenu().getName())
					.build());
		}
		return currentOrdersListByFoodtruckResponseList;
	}

	public List<OrdersListByFoodtruckResponse> getCeoOrdersAll(int ceoId) {

		User user = userRepository.findById(ceoId).get();
		FoodTruck foodTruck = foodTruckRepository.findByUser(user).get();

		List<Orders> ordersList = ordersRepository.findByCeoOrdersAll(foodTruck.getId());

		List<OrdersListByFoodtruckResponse> OrdersListByFoodtruckResponseList = new ArrayList<>();

		for (Orders orders : ordersList) {

			OrdersMenu ordersMenu = ordersMenuRepository.findByOrdersId(orders.getId());

			OrdersListByFoodtruckResponseList.add(
				OrdersListByFoodtruckResponse.builder()
					.foodtruckName(orders.getFoodTruck().getName())
					.menuName(ordersMenu.getMenu().getName())
					.build());
		}
		return OrdersListByFoodtruckResponseList;
	}

	@Transactional
	public void cancelOrders(int ceoId, int orderId) {
		Orders orders = ordersRepository.findById(orderId)
			.orElseThrow(() -> new NotFoundException(OrdersErrorMessage.NOT_FOUND_MENU));

		if(ceoId != orders.getFoodTruck().getUser().getId()){
			throw new NotFoundException(OrdersErrorMessage.NOT_FOUND_USER);
		}

		orders.setIsCanceled(true);
	}
}
