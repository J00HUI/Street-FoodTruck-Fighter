package com.ssafy.foodtruck.dto.response;

import com.ssafy.foodtruck.db.entity.Menu;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuRes {

	private Integer menuId;
	private String name;
	private Integer price;
	private String description;
	private Integer menuImageId;

	public static MenuRes of(Menu menu){

		MenuRes menuRes = new MenuResBuilder()
			.menuId(menu.getId())
			.name(menu.getName())
			.price(menu.getPrice())
			.description(menu.getDescription())
			.build();

		if(menu.getMenuImg() != null) {
			menuRes.setMenuImageId(menu.getMenuImg().getId());
		}

		return menuRes;
	}
}
