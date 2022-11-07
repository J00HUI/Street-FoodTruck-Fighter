package com.ssafy.foodtruck.db.repository;

import com.ssafy.foodtruck.db.entity.FoodTruck;
import com.ssafy.foodtruck.db.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
	Optional<Schedule> findByFoodTruck(FoodTruck foodTruck);

	@Query(value = "SELECT *\n" +
		"FROM schedule\n" +
		"WHERE latitude BETWEEN 123.10 - 0.05 AND 123.10 + 0.05\n" +
		"AND longitude BETWEEN 123.10 - 0.05 AND 123.10 + 0.05\n" +
		"And now() BETWEEN start_date AND end_date;", nativeQuery = true)
	List<Schedule> findScheduleNearBy(Double lat, Double lng);
}
