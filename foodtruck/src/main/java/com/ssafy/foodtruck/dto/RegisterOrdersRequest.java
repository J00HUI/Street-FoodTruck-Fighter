package com.ssafy.foodtruck.dto;

import com.ssafy.foodtruck.db.entity.OrdersMenu;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterOrdersRequest {

    private int foodtruckId;
    private List<Integer> menuId;
}
