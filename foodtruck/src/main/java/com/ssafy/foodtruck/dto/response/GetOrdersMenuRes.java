package com.ssafy.foodtruck.dto.response;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetOrdersMenuRes {

	private String menuName;
	private Integer count;
}
