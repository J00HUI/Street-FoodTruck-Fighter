package com.ssafy.foodtruck.db.repository;

import com.ssafy.foodtruck.db.entity.Orders;
import com.ssafy.foodtruck.db.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
	Optional<Review> findByOrders(Orders orders);
}
