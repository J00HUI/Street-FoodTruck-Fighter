package com.ssafy.foodtruck.dto.response;

import com.ssafy.foodtruck.dto.MenuDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CurrentOrdersHistoryRes {
	private Integer ordersId;
    private String foodtruckName;
	private LocalDateTime acceptTime;
	private String src;
	List<GetOrdersMenuRes> menuDtoList = new ArrayList<>();
}
