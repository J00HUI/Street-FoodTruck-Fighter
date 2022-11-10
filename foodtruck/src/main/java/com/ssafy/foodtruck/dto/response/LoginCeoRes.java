package com.ssafy.foodtruck.dto.response;

import com.ssafy.foodtruck.util.JWToken;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginCeoRes {
	private static final String BEARER = "Bearer";

	private String grantType;
	private String accessToken;
	private Integer foodTruckId;

	public static LoginCeoRes of(JWToken token, Integer foodTruckId) {
		LoginCeoRes loginCeoRes = new LoginCeoRes();
		loginCeoRes.accessToken = token.getAccessToken();
		loginCeoRes.grantType = BEARER;
		loginCeoRes.foodTruckId = foodTruckId;
		return loginCeoRes;
	}
}
