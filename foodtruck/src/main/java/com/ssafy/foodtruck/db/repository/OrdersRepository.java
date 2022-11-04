package com.ssafy.foodtruck.db.repository;

import com.ssafy.foodtruck.db.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    List<Orders> findByUserId(int userId);
}
