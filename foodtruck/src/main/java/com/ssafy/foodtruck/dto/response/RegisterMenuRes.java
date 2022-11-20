package com.ssafy.foodtruck.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterMenuRes {

	private Integer id;
	private String name;
	private Integer price;
	private String description;
}
