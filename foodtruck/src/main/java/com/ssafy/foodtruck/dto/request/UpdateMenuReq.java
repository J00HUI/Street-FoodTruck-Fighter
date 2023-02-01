package com.ssafy.foodtruck.dto.request;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateMenuReq {

	private Integer menuId;
	private String name;
	private Integer price;
	private String description;
}
