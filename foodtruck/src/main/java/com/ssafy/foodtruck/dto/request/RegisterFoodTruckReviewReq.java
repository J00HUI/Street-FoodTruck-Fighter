package com.ssafy.foodtruck.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterFoodTruckReviewReq {
	private Integer ordersId; // 주문내역 ID
	private Integer grade; // 평점
	private String content; // 리뷰
	private String src; // 사진
}
