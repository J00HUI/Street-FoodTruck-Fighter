package com.ssafy.foodtruck.db.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FileEntity extends BaseEntity {

	private String orgNm;

	private String savedNm;

	private String savedPath;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "foodtruck_id")
	private FoodTruck foodTruck;
}
