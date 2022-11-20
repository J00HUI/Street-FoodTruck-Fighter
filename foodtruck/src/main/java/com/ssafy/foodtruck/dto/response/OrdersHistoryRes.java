package com.ssafy.foodtruck.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.foodtruck.db.entity.Orders;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrdersHistoryRes {

	private Integer ordersId;
    private String foodtruckName;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime acceptTime;
	private boolean isDone;
	private boolean isCanceled;
	private boolean isReviewed;
	private String menuDescription;

//	private List<GetOrdersMenuRes> menuResList = new ArrayList<>();
	//menuName, count

	public static OrdersHistoryRes of(Orders orders, boolean isReviewed, String menuDescription){
		return new OrdersHistoryResBuilder()
			.ordersId(orders.getId())
			.foodtruckName(orders.getFoodTruck().getName())
			.acceptTime(orders.getRegDate())
			.isDone(orders.getIsDone())
			.isCanceled(orders.getIsCanceled())
			.isReviewed(isReviewed)
			.menuDescription(menuDescription)
			.build();
	}
}
