package com.ssafy.foodtruck.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime acceptTime;
	private String src;
	List<GetOrdersMenuRes> menuList = new ArrayList<>();
	//menuName, count
}
