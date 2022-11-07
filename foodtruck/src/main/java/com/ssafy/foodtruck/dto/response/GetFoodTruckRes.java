package com.ssafy.foodtruck.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.foodtruck.db.entity.Category;
import com.ssafy.foodtruck.db.entity.FoodTruck;
import com.ssafy.foodtruck.db.entity.Menu;
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
@NoArgsConstructor
public class GetFoodTruckRes {

	private List<MenuDto> menuList = new ArrayList<>(); // 메뉴리스트

	private String name; //상호명

	private Category category; //카테고리

	private String phone; //전화번호

	private String description; //설명

	private String src; //이미지

//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
//	private LocalDateTime start_date; //시작일시
//
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
//	private LocalDateTime end_date; //종료일시

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private LocalDate workingDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Seoul")
	private LocalDateTime startTime;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Seoul")
	private LocalDateTime endTime;

	private Boolean is_valid; //사용여부

	private Double latitude; // 위도

	private Double longtitue; // 경도

	private String address; //주소

	private Double grade; //평점

	private Integer numberOfPeople; //대기인원

	private Integer time; //예상시간

	public static GetFoodTruckRes of(List<MenuDto> menuList, FoodTruck foodTruck, Schedule schedule, Double grade, Integer numberOfPeople, Integer time){
		return new GetFoodTruckResBuilder()
			.name(foodTruck.getName())
			.category(foodTruck.getCategory())
			.phone(foodTruck.getPhone())
			.description(foodTruck.getDescription())
			.src(foodTruck.getSrc())
			.workingDate(schedule.getWorkingDate())
			.startTime(schedule.getStartTime())
			.endTime(schedule.getEndTime())
			.is_valid(schedule.getIsValid())
			.latitude(schedule.getLatitude())
			.longtitue(schedule.getLongitude())
			.address(schedule.getAddress())
			.grade(grade)
			.numberOfPeople(numberOfPeople)
			.time(time)
			.menuList(menuList)
			.build();
	}
}
