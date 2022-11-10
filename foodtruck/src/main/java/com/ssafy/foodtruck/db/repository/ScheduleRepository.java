package com.ssafy.foodtruck.db.repository;

import com.ssafy.foodtruck.db.entity.FoodTruck;
import com.ssafy.foodtruck.db.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
	Optional<Schedule> findByFoodTruck(FoodTruck foodTruck);

	@Query(value = "SELECT *\n" +
		"FROM schedule\n" +
		"WHERE latitude BETWEEN :lat - 0.05 AND :lat + 0.05\n" +
		"AND longitude BETWEEN :lng - 0.05 AND :lng + 0.05\n" +
		"And curdate() = working_date \n" +
		"And is_valid = true;", nativeQuery = true)
	List<Schedule> findScheduleNearBy(Double lat, Double lng);

	// 오늘 날짜에 해당하는 스케줄을 가져온다.
	@Query(value = "select *\n" +
		"from schedule\n" +
		"where foodtruck_id = :foodTruckId \n" +
		"and curdate() = working_date;", nativeQuery = true)
	Optional<Schedule> findScheduleByFoodTruckAndDate(int foodTruckId);

	// 이번달에 해당하는 스케줄을 가져온다.
	@Query(value = "select *\n" +
		"from schedule\n" +
		"where foodtruck_id = :foodTruckId \n" +
		"and working_date between :firstDate and :lastDate \n" +
		"ORDER BY group_id;", nativeQuery = true)
	List<Schedule> findScheduleByFoodTruckAndThisMonth(int foodTruckId, LocalDate firstDate, LocalDate lastDate);

	@Query(value = "SELECT max(group_id) FROM schedule;", nativeQuery = true)
	Optional<Integer> findMaxGroupId();

}
