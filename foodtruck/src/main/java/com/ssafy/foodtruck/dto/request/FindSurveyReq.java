package com.ssafy.foodtruck.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindSurveyReq {

	private Double latitude;
	private Double longitude;
}
