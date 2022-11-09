package com.ssafy.foodtruck.db.repository;

import com.ssafy.foodtruck.db.entity.Orders;
import com.ssafy.foodtruck.db.entity.Review;
import com.ssafy.foodtruck.dto.response.GetFoodTruckReviewRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.*;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
	Optional<Review> findByOrders(Orders orders);

//	@Query(value = "SELECT r.id as id, r.user_id as userId, r.orders_id as ordersId, r.src as src, r.grade as grade, r.content as content, r.reg_date as regDate\n" +
//		"FROM orders as o\n" +
//		"LEFT JOIN review as r \n" +
//		"ON o.id = r.orders_id\n" +
//		"WHERE o.foodtruck_id = :foodTruckId \n" +
//		"Order by r.reg_date desc", nativeQuery = true)
//		List<Object[]> findByFoodTruckAll(int foodTruckId);

	@Query(value = "SELECT r.id, r.reg_date, content, grade, src, orders_id, r.user_id \n" +
		"FROM orders as o\n" +
		"LEFT JOIN review as r \n" +
		"ON o.id = r.orders_id\n" +
		"WHERE o.foodtruck_id = :foodTruckId \n" +
		"Order by r.reg_date desc", nativeQuery = true)
	List<Review> findAllByFoodTruckId(int foodTruckId);
}
