package com.ssafy.foodtruck.dto.request;

import com.ssafy.foodtruck.db.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SurveyReq {

	private Double latitude; // 위도
	private Double longtitue; // 경도
	private Category category;
	private String address;
}
