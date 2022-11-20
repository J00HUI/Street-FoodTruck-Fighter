package com.ssafy.foodtruck.dto.response;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.ssafy.foodtruck.db.entity.Business;
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
	private MenuRes menuRes;
	private Integer revenue;

	public static GetBusinessRes of(Business business) {
		return GetBusinessRes.builder()
			.regDate(business.getRegDate())
			.menuRes(MenuRes.of(business.getMenu()))
			.foodtruckRes(FoodtruckRes.of(business.getFoodTruck()))
			.revenue(business.getRevenue())
			.build();
	}
}
