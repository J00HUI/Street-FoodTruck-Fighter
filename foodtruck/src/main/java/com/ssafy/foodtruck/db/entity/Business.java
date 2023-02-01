package com.ssafy.foodtruck.db.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Business extends BaseEntity{

	@NotNull
	private Integer revenue;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "foodtruck_id")
	private FoodTruck foodTruck;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id")
	private Menu menu;
}
