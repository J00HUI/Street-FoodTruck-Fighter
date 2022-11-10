package com.ssafy.foodtruck.dto.request;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterMenuReq {

	private Integer menuId;
	private Integer count;
}
