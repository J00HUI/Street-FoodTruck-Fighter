package com.ssafy.foodtruck.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Request {

	private String recipientPhoneNumber;
	private String title;
	private String content;
}
