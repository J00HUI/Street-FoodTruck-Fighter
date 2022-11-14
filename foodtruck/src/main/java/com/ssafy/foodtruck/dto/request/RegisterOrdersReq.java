package com.ssafy.foodtruck.dto.request;

import lombok.*;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterOrdersReq {

	private Integer foodTruckId;
	private List<RegisterMenuReq> menuList;
}
