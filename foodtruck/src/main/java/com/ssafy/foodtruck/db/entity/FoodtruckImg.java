package com.ssafy.foodtruck.db.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FoodtruckImg extends BaseEntity {

	private String orgNm;

	private String savedNm;

	private String savedPath;

//	@OneToOne(mappedBy = "foodtruckImg", cascade = CascadeType.ALL)
//	private FoodTruck foodTruck;
}
