package com.ssafy.foodtruck.db.repository;

import com.ssafy.foodtruck.db.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
}
