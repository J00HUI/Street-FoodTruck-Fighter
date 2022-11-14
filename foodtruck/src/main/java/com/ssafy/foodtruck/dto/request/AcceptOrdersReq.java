package com.ssafy.foodtruck.dto.request;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AcceptOrdersReq {

	private Integer ordersId;
	private Integer doneDate;
}
