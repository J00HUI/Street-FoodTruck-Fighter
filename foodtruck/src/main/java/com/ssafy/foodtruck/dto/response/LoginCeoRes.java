package com.ssafy.foodtruck.dto.response;

import com.ssafy.foodtruck.common.BaseResponseBody;
import com.ssafy.foodtruck.util.JWToken;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginCeoRes extends BaseResponseBody {
	private static final String BEARER = "Bearer";

	private String grantType;
	private String accessToken;
	private Integer foodTruckId;

	public static LoginCeoRes of(String message, JWToken token, Integer foodTruckId) {
		LoginCeoRes loginCeoRes = new LoginCeoRes();
		loginCeoRes.setMessage(message);
		loginCeoRes.setGrantType(BEARER);
		loginCeoRes.setAccessToken(token.getAccessToken());
		loginCeoRes.setFoodTruckId(foodTruckId);
		return loginCeoRes;
	}
}
