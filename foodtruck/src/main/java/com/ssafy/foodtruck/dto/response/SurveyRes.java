package com.ssafy.foodtruck.dto.response;

import com.ssafy.foodtruck.db.entity.Category;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurveyRes {

	private Category category;
	private Double latitude;
	private Double longitude;
	private String address;
}
