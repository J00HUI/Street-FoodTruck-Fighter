package com.ssafy.foodtruck.util;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class JWToken {

    private String accessToken;
    private String refreshToken;
}
