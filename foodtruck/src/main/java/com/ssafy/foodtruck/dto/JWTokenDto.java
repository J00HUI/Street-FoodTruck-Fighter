package com.ssafy.foodtruck.dto;

import com.ssafy.foodtruck.util.JWToken;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JWTokenDto {

    private static final String BEARER = "Bearer";

    private String grantType;
    private String accessToken;

    public static JWTokenDto of(JWToken token) {
        JWTokenDto jwToken = new JWTokenDto();
        jwToken.accessToken = token.getAccessToken();
        jwToken.grantType = BEARER;
        return jwToken;
    }
}
