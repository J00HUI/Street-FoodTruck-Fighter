package com.ssafy.foodtruck.db.repository;

import com.ssafy.foodtruck.db.entity.FoodTruck;
import com.ssafy.foodtruck.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodTruckRepository extends JpaRepository<FoodTruck, Integer> {
	Optional<FoodTruck> findByUser(User user);
}
