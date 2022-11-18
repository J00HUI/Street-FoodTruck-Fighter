package com.ssafy.foodtruck.dto.response;

import com.ssafy.foodtruck.db.entity.Menu;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuReq {

	private String name;
	private Integer price;
	private String description;
}
