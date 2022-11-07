package com.ssafy.foodtruck.dto.response;

import com.ssafy.foodtruck.db.entity.Category;
import com.ssafy.foodtruck.db.entity.FoodTruck;
import com.ssafy.foodtruck.db.entity.Schedule;
import com.ssafy.foodtruck.dto.MenuDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetNearFoodTruckRes {
	private Integer foodTruckId;		// 푸드트럭 Id
	private List<MenuDto> menuList = new ArrayList<>(); // 메뉴리스트
	private String name; //상호명
	private Category category; //카테고리
	private String phone; //전화번호
	private String description; //설명
	private String src; //이미지

	private LocalDateTime start_date; //시작일시
	private LocalDateTime end_date; //종료일시
	private Boolean is_valid; //사용여부
	private Double latitude; // 위도
	private Double longtitue; // 경도
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
			.src(foodTruck.getSrc())
			.start_date(schedule.getStartDate())
			.end_date(schedule.getEndDate())
			.is_valid(schedule.getIsValid())
			.latitude(schedule.getLatitude())
			.longtitue(schedule.getLongitude())
			.address(schedule.getAddress())
			.grade(grade)
			.build();
	}
}
