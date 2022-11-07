package com.ssafy.foodtruck.db.repository;

import com.ssafy.foodtruck.db.entity.FoodTruck;
import com.ssafy.foodtruck.db.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
	Optional<Schedule> findByFoodTruck(FoodTruck foodTruck);
}
