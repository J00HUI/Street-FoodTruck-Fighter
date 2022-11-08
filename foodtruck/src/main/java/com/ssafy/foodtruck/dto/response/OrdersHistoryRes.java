package com.ssafy.foodtruck.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrdersHistoryRes {

    private String foodtruckName;
    private String menuName;
	private LocalDateTime acceptTime;
}
