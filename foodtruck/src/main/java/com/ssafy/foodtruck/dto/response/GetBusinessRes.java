package com.ssafy.foodtruck.dto.response;

import com.ssafy.foodtruck.db.entity.Business;
import com.ssafy.foodtruck.db.entity.FoodTruck;
import com.ssafy.foodtruck.db.entity.Menu;
import com.ssafy.foodtruck.dto.MenuDto;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetBusinessRes {

	private LocalDateTime regDate;
	private FoodtruckRes foodtruckRes;
	private MenuDto menuDto;
	private Integer revenue;

	public static GetBusinessRes of(Business business) {
		return GetBusinessRes.builder()
			.regDate(business.getRegDate())
			.menuDto(MenuDto.of(business.getMenu()))
			.foodtruckRes(FoodtruckRes.of(business.getFoodTruck()))
			.revenue(business.getRevenue())
			.build();
	}
}
