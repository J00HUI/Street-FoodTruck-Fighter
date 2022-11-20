package com.ssafy.foodtruck.dto.response;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrdersHistoryAllRes {

	private String orderDate;
	List<OrdersHistoryRes> ordersHistoryResList = new ArrayList<>();
}
