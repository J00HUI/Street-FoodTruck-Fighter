package com.ssafy.foodtruck.db.repository;

import com.ssafy.foodtruck.db.entity.FoodTruck;
import com.ssafy.foodtruck.db.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
	List<Menu> findAllByFoodTruck(FoodTruck foodTruck);
	Optional<Menu> findMenuByName(String menuName);
}
