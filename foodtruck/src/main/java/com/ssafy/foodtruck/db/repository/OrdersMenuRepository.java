package com.ssafy.foodtruck.db.repository;

import com.ssafy.foodtruck.db.entity.Orders;
import com.ssafy.foodtruck.db.entity.OrdersMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdersMenuRepository extends JpaRepository<OrdersMenu, Integer> {

	List<OrdersMenu> findAllByOrders(Orders orders);
}
