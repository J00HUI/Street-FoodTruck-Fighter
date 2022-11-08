package com.ssafy.foodtruck.dto.request;

import lombok.*;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterOrdersReq {

	private Integer foodtruckId;
	private List<Integer> menuIdList;
}
