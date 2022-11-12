package com.ssafy.foodtruck.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.foodtruck.db.entity.Category;
import com.ssafy.foodtruck.db.entity.FoodTruck;
import com.ssafy.foodtruck.db.entity.Schedule;
import com.ssafy.foodtruck.dto.MenuDto;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetNearFoodTruckRes {
	private Integer foodTruckId;		// 푸드트럭 Id
	private List<MenuDto> menuList = new ArrayList<>(); // 메뉴리스트
	private String name; //상호명
	private Category category; //카테고리
	private String phone; //전화번호
	private String description; //설명
	private String src; //이미지

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private LocalDate workingDate;	// 날짜

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Seoul")
	private LocalDateTime startTime; // 시작일시

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Seoul")
	private LocalDateTime endTime; // 종료일시
	private Double latitude; // 위도
	private Double longitude; // 경도
	private String address; //주소

	private Double grade; //평점

	public static GetNearFoodTruckRes of(List<MenuDto> menuList, FoodTruck foodTruck, Schedule schedule, Double grade){
		return new GetNearFoodTruckResBuilder()
			.foodTruckId(foodTruck.getId())
			.menuList(menuList)
			.name(foodTruck.getName())
			.category(foodTruck.getCategory())
			.phone(foodTruck.getPhone())
			.description(foodTruck.getDescription())
			.workingDate(schedule.getWorkingDate())
			.startTime(schedule.getStartTime())
			.endTime(schedule.getEndTime())
			.latitude(schedule.getLatitude())
			.longitude(schedule.getLongitude())
			.address(schedule.getAddress())
			.grade(grade)
			.build();
	}
}
