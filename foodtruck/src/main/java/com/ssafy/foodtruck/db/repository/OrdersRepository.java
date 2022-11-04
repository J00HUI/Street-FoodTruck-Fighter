package com.ssafy.foodtruck.db.repository;

import com.ssafy.foodtruck.db.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    @Query(value = "SELECT *\n" +
            "FROM orders\n" +
            "WHERE user_id = :userId\n" +
            "AND is_accepted = 1 \n" +
            "AND is_done = 0", nativeQuery = true)
    List<Orders> findByCustomerOrders(int userId);

    @Query(value = "SELECT * \n" +
            "FROM orders \n" +
            "WHERE user_id = :userId \n" +
            "AND is_done = 1", nativeQuery = true)
    List<Orders> findByCustomerOrdersAll(int userId);

    @Query(value = "SELECT * \n" +
            "FROM orders \n" +
            "WHERE foodtruck_id = :foodtruckId \n" +
            "AND is_accepted = 1 \n" +
            "AND is_done = 0;", nativeQuery = true)
    List<Orders> findByCeoOrders(int foodtruckId);

    @Query(value = "SELECT *\n" +
            "FROM orders\n" +
            "WHERE is_done = 1", nativeQuery = true)
    List<Orders> findByCeoOrdersAll(int foodtruckId);
}
