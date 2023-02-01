package com.ssafy.foodtruck.dto.request;

import com.ssafy.foodtruck.db.entity.Category;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RegisterFoodtruckReq {

	private String name; //상호명
	private Category category; //카테고리
	private String phone; //전화번호
	private String description; //설명
	private String address; //주소
	private Double latitude; // 위도
	private Double longitude; // 경도
}
