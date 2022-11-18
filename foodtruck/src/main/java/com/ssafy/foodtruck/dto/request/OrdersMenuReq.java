package com.ssafy.foodtruck.dto.request;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrdersMenuReq {

	private Integer menuId;
	private Integer count;
}

//	private List<MenuReq> menuList; // 메뉴리스트
