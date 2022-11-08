package com.ssafy.foodtruck.dto.response;

import com.ssafy.foodtruck.db.entity.Category;
import com.ssafy.foodtruck.db.entity.FoodTruck;
import com.ssafy.foodtruck.db.entity.Menu;
import com.ssafy.foodtruck.db.entity.Schedule;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FoodtruckRes {
	private Integer id;
	private String name;
	private String src;
	private Category category;
	private String phone;
	private String description;
	private List<Menu> menuList;
	private List<Schedule> scheduleList;

	public static FoodtruckRes of(FoodTruck foodtruck) {
		return FoodtruckRes.builder()
			.id(foodtruck.getId())
			.category(foodtruck.getCategory())
			.name(foodtruck.getName())
			.src(foodtruck.getSrc())
			.phone(foodtruck.getPhone())
			.description(foodtruck.getDescription())
			.menuList(foodtruck.getMenuList())
			.scheduleList(foodtruck.getScheduleList())
			.build();
	}
}
