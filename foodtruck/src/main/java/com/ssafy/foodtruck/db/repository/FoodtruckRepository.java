package com.ssafy.foodtruck.db.repository;

import com.ssafy.foodtruck.db.entity.FoodTruck;
import com.ssafy.foodtruck.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.List;

public interface FoodtruckRepository extends JpaRepository<FoodTruck, Integer> {
	Optional<FoodTruck> findByUser(User user);

	@Query(value = "select distinct f.id\n" +
		"from food_truck f\n" +
		"right join menu m\n" +
		"on f.id = m.foodtruck_id\n" +
		"where f.name like %:keyword% \n" +
		"or f.category like %:keyword% \n" +
		"or f.description like %:keyword% \n" +
		"or m.name like %:keyword% \n" +
		"or m.description like %:keyword% ;", nativeQuery = true)
	List<Integer> findAllByKeyword(String keyword);
}
