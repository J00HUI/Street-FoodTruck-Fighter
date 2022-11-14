package com.ssafy.foodtruck.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrdersListByFoodtruckRes {

	private String foodtruckName;
    private String menuName;
	private LocalDateTime acceptTime;
}
