package com.ssafy.foodtruck.dto.response;

import com.ssafy.foodtruck.db.entity.Menu;
import com.ssafy.foodtruck.db.entity.Orders;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterOrdersRes {

	private Orders orders;
	private String payMenuName;
	private Integer totalQuantity;
	private Integer totalAmount;
}
