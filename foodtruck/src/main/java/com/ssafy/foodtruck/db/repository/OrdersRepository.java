package com.ssafy.foodtruck.db.repository;

import com.ssafy.foodtruck.db.entity.FoodTruck;
import com.ssafy.foodtruck.db.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    @Query(value = "SELECT * \n" +
            "FROM orders \n" +
            "WHERE user_id = :customerId \n" +
            "AND is_accepted = 1 \n" +
            "AND is_done = 0 \n" +
			"AND is_canceled = 0;", nativeQuery = true)
    List<Orders> findCustomerOrders(int customerId);

    @Query(value = "SELECT * \n" +
            "FROM orders \n" +
            "WHERE user_id = :customerId \n" +
            "AND is_done = 1", nativeQuery = true)
    List<Orders> findByCustomerOrdersAll(int customerId);

    @Query(value = "SELECT * \n" +
            "FROM orders \n" +
            "WHERE foodtruck_id = :foodtruckId \n" +
            "AND is_accepted = 1 \n" +
            "AND is_done = 0", nativeQuery = true)
    List<Orders> findByCeoOrders(int foodtruckId);

    @Query(value = "SELECT * \n" +
            "FROM orders \n" +
            "WHERE foodtruck_id = :foodtruckId \n" +
			"AND is_done = 1", nativeQuery = true)
    List<Orders> findByCeoOrdersAll(int foodtruckId);

	List<Orders> findByFoodTruck(FoodTruck foodTruck);

	@Query(value = "SELECT count(om.menu_id) \n" +
		"FROM orders o \n" +
		"JOIN orders_menu om \n" +
		"ON o.id = om.orders_id \n" +
		"WHERE o.user_Id = :customerId \n" +
		"AND o.is_accepted = 1 \n" +
		"AND o.is_done = 0 \n" +
		"GROUP BY om.menu_id", nativeQuery = true)
	int findByCount(int customerId);
}
