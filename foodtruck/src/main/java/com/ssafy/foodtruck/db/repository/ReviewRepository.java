package com.ssafy.foodtruck.db.repository;

import com.ssafy.foodtruck.db.entity.Orders;
import com.ssafy.foodtruck.db.entity.Review;
import com.ssafy.foodtruck.db.entity.User;
import com.ssafy.foodtruck.dto.response.GetFoodTruckReviewRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.*;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
	Optional<Review> findByOrders(Orders orders);

	@Query(value = "SELECT r.id, r.user_id, r.orders_id, r.src, grade, content, r.reg_date \n" +
		"FROM orders as o\n" +
		"INNER JOIN review as r \n" +
		"ON o.id = r.orders_id\n" +
		"WHERE o.foodtruck_id = :foodTruckId \n" +
		"Order by r.reg_date desc", nativeQuery = true)
	List<Review> findAllByFoodTruckId(int foodTruckId);

	Optional<Review> findReviewByOrdersAndUser(Orders orders, User user);
}
