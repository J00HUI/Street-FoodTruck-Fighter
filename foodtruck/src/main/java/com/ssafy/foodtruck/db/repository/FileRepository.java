package com.ssafy.foodtruck.db.repository;

import com.ssafy.foodtruck.db.entity.FoodtruckImg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FoodtruckImg, Integer> {
}
