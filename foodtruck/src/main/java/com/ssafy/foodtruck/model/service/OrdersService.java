package com.ssafy.foodtruck.model.service;

import com.ssafy.foodtruck.db.entity.*;
import com.ssafy.foodtruck.db.repository.*;
import com.ssafy.foodtruck.dto.request.RegisterOrdersReq;
import com.ssafy.foodtruck.dto.request.AcceptOrdersReq;
import com.ssafy.foodtruck.dto.response.CurrentOrdersHistoryRes;
import com.ssafy.foodtruck.dto.response.CurrentOrdersListByFoodtruckRes;
import com.ssafy.foodtruck.dto.response.OrdersHistoryRes;
import com.ssafy.foodtruck.dto.response.OrdersListByFoodtruckRes;
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
	private final FoodTruckRepository foodTruckRepository;

	@Transactional
	public void registerOrders(RegisterOrdersReq registerOrdersReq, User user) {
		FoodTruck foodTruck = foodTruckRepository.findById(registerOrdersReq.getFoodtruckId())
			.orElseThrow(() -> new NotFoundException(OrdersErrorMessage.NOT_FOUND_FOODTRUCK));
		final Orders orders = Orders.builder()
			.user(user)
			.foodTruck(foodTruck)
			.build();
		Orders savedOrders = ordersRepository.save(orders);

		List<Integer> menuIdList = registerOrdersReq.getMenuIdList();
		for(int menuId : menuIdList){
			Menu menu = menuRepository.findById(menuId)
				.orElseThrow(() -> new NotFoundException(OrdersErrorMessage.NOT_FOUND_MENU));

			ordersMenuRepository.save(OrdersMenu.builder()
				.orders(savedOrders)
				.menu(menu)
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
		List<Orders> ordersList = ordersRepository.findByCustomerOrders(customerId);
		List<CurrentOrdersHistoryRes> currentOrdersHistoryResList = new ArrayList<>();

		for (Orders orders : ordersList) {
			OrdersMenu ordersMenu = ordersMenuRepository.findByOrdersId(orders.getId());
			currentOrdersHistoryResList.add(
				CurrentOrdersHistoryRes.builder()
					.foodtruckName(orders.getFoodTruck().getName())
					.menuName(ordersMenu.getMenu().getName())
					.build());
		}
		return currentOrdersHistoryResList;
	}

	public List<OrdersHistoryRes> getCustomerOrdersAll(int customerId) {
		List<Orders> ordersList = ordersRepository.findByCustomerOrdersAll(customerId);
		List<OrdersHistoryRes> ordersHistoryResList = new ArrayList<>();

		for (Orders orders : ordersList) {
			OrdersMenu ordersMenu = ordersMenuRepository.findByOrdersId(orders.getId());
			ordersHistoryResList.add(
				OrdersHistoryRes.builder()
					.foodtruckName(orders.getFoodTruck().getName())
					.menuName(ordersMenu.getMenu().getName())
					.build());
		}
		return ordersHistoryResList;
	}

	public List<CurrentOrdersListByFoodtruckRes> getCeoOrders(int ceoId) {
		User user = userRepository.findById(ceoId)
			.orElseThrow(() -> new NotFoundException(OrdersErrorMessage.NOT_FOUND_USER));
		FoodTruck foodTruck = foodTruckRepository.findByUser(user)
			.orElseThrow(() -> new NotFoundException(OrdersErrorMessage.NOT_FOUND_FOODTRUCK));
		List<Orders> ordersList = ordersRepository.findByCeoOrders(foodTruck.getId());
		List<CurrentOrdersListByFoodtruckRes> currentOrdersListByFoodtruckResponseList = new ArrayList<>();

		for (Orders orders : ordersList) {
			OrdersMenu ordersMenu = ordersMenuRepository.findByOrdersId(orders.getId());
			currentOrdersListByFoodtruckResponseList.add(
				CurrentOrdersListByFoodtruckRes.builder()
					.foodtruckName(orders.getFoodTruck().getName())
					.menuName(ordersMenu.getMenu().getName())
					.build());
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

		for (Orders orders : ordersList) {
			OrdersMenu ordersMenu = ordersMenuRepository.findByOrdersId(orders.getId());

			ordersListByFoodtruckResponseList.add(
				OrdersListByFoodtruckRes.builder()
					.foodtruckName(orders.getFoodTruck().getName())
					.menuName(ordersMenu.getMenu().getName())
					.build());
		}
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
}
