package com.ssafy.foodtruck.dto.request;

import com.ssafy.foodtruck.db.entity.Category;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SurveyReq {

	private Double latitude;
	private Double longitude;
	private Category category;
	private String address;
}
