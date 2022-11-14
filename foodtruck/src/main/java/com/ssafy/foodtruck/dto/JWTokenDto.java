package com.ssafy.foodtruck.dto;

import com.ssafy.foodtruck.common.BaseResponseBody;
import com.ssafy.foodtruck.util.JWToken;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JWTokenDto extends BaseResponseBody {

	private static final String BEARER = "Bearer";

	private String grantType;
	private String accessToken;

	public static JWTokenDto of(String message, JWToken token) {
		JWTokenDto jwToken = new JWTokenDto();
		jwToken.setMessage(message);
		if (token != null)
			jwToken.setAccessToken(token.getAccessToken());
		else jwToken.setAccessToken(null);
		jwToken.setGrantType(BEARER);
		return jwToken;
	}
}
