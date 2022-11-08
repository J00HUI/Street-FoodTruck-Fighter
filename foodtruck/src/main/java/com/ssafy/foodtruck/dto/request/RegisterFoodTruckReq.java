package com.ssafy.foodtruck.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.foodtruck.db.entity.Category;
import com.ssafy.foodtruck.dto.MenuDto;
import com.ssafy.foodtruck.dto.ScheduleDateDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RegisterFoodTruckReq {
	private List<MenuDto> menuList; // 메뉴리스트

	private String name; //상호명
	private String src; //이미지
	private Category category; //카테고리
	private String phone; //전화번호
	private String description; //설명

	private String address; //주소
	private Double latitude; // 위도
	private Double longtitue; // 경도

	List<ScheduleDateDto> dateDtoList;	// 운영시간리스트
}
