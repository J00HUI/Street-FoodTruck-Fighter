package com.ssafy.foodtruck.dto;

import lombok.*;
import org.checkerframework.checker.units.qual.A;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CurrentOrdersListByFoodtruckResponse {

    private String menuName;
}
