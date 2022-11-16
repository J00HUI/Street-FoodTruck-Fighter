package com.ssafy.foodtruck.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RegisterFoodtruckReviewReq {

	private Integer ordersId; // 주문내역 ID
	private Integer grade; // 평점
	private String content; // 리뷰
}
