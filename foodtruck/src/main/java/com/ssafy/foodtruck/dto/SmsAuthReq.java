package com.ssafy.foodtruck.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SmsAuthReq {

	private String phoneNumber;
	private String authToken;
}
