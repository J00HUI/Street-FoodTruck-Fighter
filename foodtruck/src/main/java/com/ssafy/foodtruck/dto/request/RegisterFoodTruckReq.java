package com.ssafy.foodtruck.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.foodtruck.db.entity.Category;
import com.ssafy.foodtruck.dto.MenuDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
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

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private String start_date; //시작일시

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private String end_date; //종료일시
}
