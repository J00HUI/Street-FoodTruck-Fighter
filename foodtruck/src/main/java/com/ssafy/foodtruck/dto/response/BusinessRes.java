package com.ssafy.foodtruck.dto.response;

import com.ssafy.foodtruck.db.entity.Business;
import lombok.*;

import java.time.format.DateTimeFormatter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BusinessRes {

	private String regDate;
	private MenuRes menuRes;
	private Integer revenue;

	public static BusinessRes of(Business business) {
		return BusinessRes.builder()
			.regDate(business.getRegDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
			.menuRes(MenuRes.of(business.getMenu()))
			.revenue(business.getRevenue())
			.build();
	}
}
