package com.ssafy.foodtruck.util;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JWToken {
    private String accessToken;
    private String refreshToken;
}
