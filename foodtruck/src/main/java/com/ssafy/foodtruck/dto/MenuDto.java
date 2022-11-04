package com.ssafy.foodtruck.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {

	private String name;
	private Integer price;
	private String description;
	private String src;
}
