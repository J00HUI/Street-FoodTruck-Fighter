package com.ssafy.foodtruck.dto;

import com.ssafy.foodtruck.db.entity.Menu;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuDto {

	private Integer id;
	private String name;
	private Integer price;
	private String description;
	private String src;

	public static MenuDto of(Menu menu){
		return new MenuDtoBuilder()
			.id(menu.getId())
			.name(menu.getName())
			.price(menu.getPrice())
			.description(menu.getDescription())
			.src(menu.getSrc())
			.build();
	}
}
