package com.ssafy.foodtruck.db.entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MenuImg extends BaseEntity{

	private String orgNm;

	private String savedNm;

	private String savedPath;

//	@OneToOne(mappedBy = "menuImg")
//	private Menu menu;
}
