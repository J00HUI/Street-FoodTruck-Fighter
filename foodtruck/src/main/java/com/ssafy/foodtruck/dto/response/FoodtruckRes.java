package com.ssafy.foodtruck.dto.response;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.ssafy.foodtruck.db.entity.Category;
import com.ssafy.foodtruck.db.entity.FoodTruck;
import com.ssafy.foodtruck.db.entity.Menu;
import com.ssafy.foodtruck.db.entity.Schedule;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FoodtruckRes {
	private Integer id;
	private String name;
	private Category category;
	private String phone;
	private String description;
	private List<MenuRes> menuList;
	private List<GetScheduleRes> scheduleList;

	public static FoodtruckRes of(FoodTruck foodtruck) {

		FoodtruckRes foodtruckRes = FoodtruckRes.builder()
			.id(foodtruck.getId())
			.category(foodtruck.getCategory())
			.name(foodtruck.getName())
			.phone(foodtruck.getPhone())
			.description(foodtruck.getDescription())
			.build();

		List<MenuRes> menuResList = new ArrayList<>();
		for(Menu menu : foodtruck.getMenuList()) {
			menuResList.add(MenuRes.of(menu));
		}
		foodtruckRes.setMenuList(menuResList);

		List<GetScheduleRes> scheduleList = new ArrayList<>();
		for(Schedule schedule : foodtruck.getScheduleList()) {
			scheduleList.add(GetScheduleRes.of(schedule));
		}

		foodtruckRes.setScheduleList(scheduleList);


		return foodtruckRes;
	}
}
