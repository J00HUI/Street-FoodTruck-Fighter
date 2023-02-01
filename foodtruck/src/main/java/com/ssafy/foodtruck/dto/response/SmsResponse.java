package com.ssafy.foodtruck.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SmsResponse {

	private String requestId;
	private LocalDateTime requestTime;
	private String statusCode;
	private String statusName;
}
