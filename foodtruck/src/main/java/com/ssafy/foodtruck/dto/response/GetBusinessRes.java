package com.ssafy.foodtruck.dto.response;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetBusinessRes {
	private String regDate;
	private List<BusinessRes> businessResList = new ArrayList<>();
}
