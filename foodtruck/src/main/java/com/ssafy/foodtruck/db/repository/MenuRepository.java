package com.ssafy.foodtruck.db.repository;

import com.ssafy.foodtruck.db.entity.FoodTruck;
import com.ssafy.foodtruck.db.entity.Menu;
import com.ssafy.foodtruck.dto.MenuDto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
	List<Menu> findMenuByFoodTruck(FoodTruck foodTruck);
}
