package com.ssafy.foodtruck.db.repository;

import com.ssafy.foodtruck.db.entity.OrdersMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdersMenuRepository extends JpaRepository<OrdersMenu, Integer> {

    @Query(value = "SELECT *\n" +
            "FROM orders o\n" +
            "LEFT JOIN orders_menu om\n" +
            "ON o.id = om.orders_id\n" +
            "WHERE o.foodtruck_id LIKE :foodtruckId", nativeQuery = true)
    OrdersMenu findByCeoOrders(int foodtruckId);

	OrdersMenu findByOrdersId(Integer ordersId);
}
