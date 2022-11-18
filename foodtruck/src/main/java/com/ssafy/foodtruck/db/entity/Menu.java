package com.ssafy.foodtruck.db.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Menu extends BaseEntity {

	@NotNull
	@Column(length = 50)
	private String name;

	@NotNull
	private Integer price;

	@Column(length = 200)
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "foodtruck_id")
	private FoodTruck foodTruck;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "menu_img_id")
	private MenuImg menuImg;

	@Builder.Default
	@OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
	private List<OrdersMenu> ordersMenuList = new ArrayList<>();

}
