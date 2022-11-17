package com.ssafy.foodtruck.dto.response;

import com.ssafy.foodtruck.db.entity.Menu;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuReq {

	private Integer menuId;
	private String name;
	private Integer price;
	private String description;
//	private String src;

	public static MenuReq of(Menu menu){
		return new MenuReqBuilder()
			.menuId(menu.getId())
			.name(menu.getName())
			.price(menu.getPrice())
			.description(menu.getDescription())
//			.src(menu.getSrc())
			.build();
	}
}
