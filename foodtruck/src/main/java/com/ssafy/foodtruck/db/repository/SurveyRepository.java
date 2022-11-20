package com.ssafy.foodtruck.db.repository;

import com.ssafy.foodtruck.db.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Integer> {

	//추후에 날짜 조건도 추가해주면 좋을 듯
	@Query(value = "SELECT * \n" +
		"FROM survey \n" +
		"WHERE latitude BETWEEN :latitude - 0.5 AND :latitude + 0.5 \n" +
		"AND longitude BETWEEN :longitude - 0.5 AND :longitude + 0.5", nativeQuery = true)
	List<Survey> findSurveyNearby(Double latitude, Double longitude);
}
