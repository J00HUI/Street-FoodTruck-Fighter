package com.ssafy.foodtruck.db.repository;

import com.ssafy.foodtruck.db.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BusinessRepository extends JpaRepository<Business, Integer> {

	@Query(value = "select om.menu_id from orders o\n" +
		"inner join orders_menu om\n" +
		"on o.id = om.orders_id\n" +
		"where date(o.done_date) = date(now())\n" +
		"and o.foodtruck_id = :foodtruck_id \n" +
		"group by om.menu_id\n" +
		"order by om.menu_id", nativeQuery = true)
	List<Integer> findMenuIdByFoodTruckId(Integer foodtruck_id);

	@Query(value = "select sum(om.count) from orders o\n" +
		"inner join orders_menu om\n" +
		"on o.id = om.orders_id\n" +
		"where date(o.done_date) = date(now())\n" +
		"and o.foodtruck_id = :foodtruck_id \n" +
		"group by om.menu_id\n" +
		"order by om.menu_id", nativeQuery = true)
	List<Integer> findCountByFoodTruckId(Integer foodtruck_id);

	@Query(value = "SELECT * FROM business\n" +
		"where foodtruck_id = :foodtruck_id", nativeQuery = true)
	Optional<List<Business>> findBusinessByFoodtruckId(Integer foodtruck_id);

	@Modifying
	@Transactional
	@Query(value = "delete from business\n" +
		"where foodtruck_id = :foodtruck_id \n" +
		"and date(reg_date) = date(now())", nativeQuery = true)
	void deleteAllByFoodTruck(Integer foodtruck_id);
}
