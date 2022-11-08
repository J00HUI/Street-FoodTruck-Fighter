package com.ssafy.foodtruck.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CurrentOrdersHistoryRes {

    private String foodtruckName;
    private String menuName;
	private LocalDateTime acceptTime;
	private Integer count;
}
