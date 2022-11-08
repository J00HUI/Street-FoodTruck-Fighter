package com.ssafy.foodtruck.dto.request;

import com.ssafy.foodtruck.db.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetNearFoodTruckReq {

	private Double lat;
	private Double lng;
	private Category category;
}
