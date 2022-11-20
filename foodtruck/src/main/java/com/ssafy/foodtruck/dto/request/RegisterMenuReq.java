package com.ssafy.foodtruck.dto.request;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterMenuReq {

	private List<MenuReq> menuReqList = new ArrayList<>();
}
