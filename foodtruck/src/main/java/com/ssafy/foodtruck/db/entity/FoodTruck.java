package com.ssafy.foodtruck.db.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.ssafy.foodtruck.dto.request.RegisterFoodtruckReq;
import com.sun.istack.NotNull;
import lombok.*;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FoodTruck extends BaseEntity {

	@Unique
	@NotNull
	@Column(length = 50)
	private String name;

	@Enumerated(value = EnumType.STRING)
	@NotNull
	private Category category;

	@Column(length = 50)
	private String phone;

	@Column(length = 200)
	private String description;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "foodtruck_img_id")
	private FoodtruckImg foodtruckImg;

	@Builder.Default
	@OneToMany(mappedBy = "foodTruck", cascade = CascadeType.ALL)
	private List<Menu> menuList = new ArrayList<>();

	@OneToMany(mappedBy = "foodTruck", cascade = CascadeType.ALL)
	private List<Schedule> scheduleList = new ArrayList<>();

	@OneToMany(mappedBy = "foodTruck", cascade = CascadeType.ALL)
	private List<Orders> ordersList = new ArrayList<>();

	@OneToMany(mappedBy = "foodTruck", cascade = CascadeType.ALL)
	private List<Business> businessList = new ArrayList<>();

	public void update(final RegisterFoodtruckReq registerFoodTruckReq) {
		this.name = registerFoodTruckReq.getName();
		this.category = registerFoodTruckReq.getCategory();
		this.phone = registerFoodTruckReq.getPhone();
		this.description = registerFoodTruckReq.getDescription();
	}

	public void setFoodtruckImg(FoodtruckImg foodtruckImg) {
		this.foodtruckImg = foodtruckImg;
	}
}
