package com.ssafy.foodtruck.model.service;

import com.ssafy.foodtruck.db.entity.Orders;
import com.ssafy.foodtruck.db.entity.OrdersErrorMessage;
import com.ssafy.foodtruck.db.entity.OrdersMenu;
import com.ssafy.foodtruck.db.repository.OrdersMenuRepository;
import com.ssafy.foodtruck.db.repository.OrdersRepository;
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

	public void registerOrders(int customerId, RegisterOrdersRequest registerOrdersRequest) {

	}

	@Transactional
	public void acceptOrders(int ceoId, int ordersId) {
		Orders orders = ordersRepository.findById(ordersId)
			.orElseThrow(() -> new NotFoundException(OrdersErrorMessage.NOT_FOUND_MENU));

		if (ceoId == orders.getUser().getId()) {
			orders.setIsAccepted(true);
		}
	}

	public List<CurrentOrdersHistoryResponse> getCustomerOrders(int customerId) {
		List<Orders> ordersList = ordersRepository.findByCustomerOrders(customerId);
		OrdersMenu ordersMenu = ordersMenuRepository.findById(customerId)
			.orElseThrow(() -> new NotFoundException(OrdersErrorMessage.NOT_FOUND_USER));

		List<CurrentOrdersHistoryResponse> currentOrdersHistoryResponses = new ArrayList<>();

		for (Orders orders : ordersList) {
			currentOrdersHistoryResponses.add(
				CurrentOrdersHistoryResponse.builder()
					.foodtruckName(orders.getFoodTruck().getName())
					.menuName(ordersMenu.getMenu().getName())
					.build());
		}
		return currentOrdersHistoryResponses;
	}

	public List<OrdersHistoryResponse> getCustomerOrdersAll(int customerId) {
		List<Orders> ordersList = ordersRepository.findByCustomerOrdersAll(customerId);
		OrdersMenu ordersMenu = ordersMenuRepository.findById(customerId)
			.orElseThrow(() -> new NotFoundException(OrdersErrorMessage.NOT_FOUND_USER));

		List<OrdersHistoryResponse> ordersHistoryResponses = new ArrayList<>();

		for (Orders orders : ordersList) {
			ordersHistoryResponses.add(
				OrdersHistoryResponse.builder()
					.foodtruckName(orders.getFoodTruck().getName())
					.menuName(ordersMenu.getMenu().getName())
					.build());
		}
		return ordersHistoryResponses;
	}

	public List<CurrentOrdersListByFoodtruckResponse> getCeoOrders(int ceoId, int foodtruckId) {
		List<Orders> ordersList = ordersRepository.findByCeoOrders(foodtruckId);
		OrdersMenu ordersMenu = ordersMenuRepository.findByCeoOrders(foodtruckId);

		List<CurrentOrdersListByFoodtruckResponse> currentOrdersListByFoodtruckResponses = new ArrayList<>();

		for (Orders orders : ordersList) {
			currentOrdersListByFoodtruckResponses.add(
				CurrentOrdersListByFoodtruckResponse.builder()
					.menuName(ordersMenu.getMenu().getName())
					.build());
		}
		return currentOrdersListByFoodtruckResponses;
	}

	public List<OrdersListByFoodtruckResponse> getCeoOrdersAll(int ceoId, int foodtruckId) {
		List<Orders> ordersList = ordersRepository.findByCeoOrdersAll(foodtruckId);
		OrdersMenu ordersMenu = ordersMenuRepository.findByCeoOrders(foodtruckId);

		List<OrdersListByFoodtruckResponse> ordersListByFoodtruckResponses = new ArrayList<>();

		for (Orders orders : ordersList) {

			if (orders.getIsDone()) {
				ordersListByFoodtruckResponses.add(
					OrdersListByFoodtruckResponse.builder()
						.menuName(ordersMenu.getMenu().getName())
						.build());
			}
		}
		return ordersListByFoodtruckResponses;
	}

	@Transactional
	public void cancelOrders(int ceoId, int orderId) {
		Orders orders = ordersRepository.findById(orderId)
			.orElseThrow(() -> new NotFoundException(OrdersErrorMessage.NOT_FOUND_MENU));

		if (ceoId == orders.getUser().getId()) {
			orders.setIsCanceled(true);
		}
	}
}
