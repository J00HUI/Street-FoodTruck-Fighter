package com.ssafy.foodtruck.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 서버 요청에대한 기본 응답값(바디) 정의.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("BaseResponseBody")
public class BaseResponseBody {
	@ApiModelProperty(name="응답 메시지", example = "정상")
	String message = null;

	public static BaseResponseBody of(String message) {
		BaseResponseBody body = new BaseResponseBody();
		body.message = message;
		return body;
	}
}
