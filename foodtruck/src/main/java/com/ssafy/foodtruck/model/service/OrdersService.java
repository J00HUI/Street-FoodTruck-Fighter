package com.ssafy.foodtruck.model.service;

import com.ssafy.foodtruck.db.entity.*;
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

    public void registerOrders(int userId, RegisterOrdersRequest registerOrdersRequest) {

    }

    @Transactional
    public void acceptOrders(int userId, int ordersId) {
        Orders orders = ordersRepository.findById(ordersId)
                .orElseThrow(() -> new NotFoundException(OrdersErrorMessage.NOT_FOUND_MENU));
        orders.setIsAccepted(true);
    }
}
