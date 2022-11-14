package com.ssafy.foodtruck.controller;

import com.ssafy.foodtruck.dto.request.PayReadyReq;
import com.ssafy.foodtruck.dto.response.PayReadyRes;
import com.ssafy.foodtruck.model.service.PayService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 카카오페이 관련 API 요청 처리를 위한 컨트롤러 정의
 */
@RestController
@RequestMapping("/v1/pay")
@RequiredArgsConstructor
@Api(value = "결제 API", tags = {"Pay"})
public class PayController {

	private final PayService payService;

	// 결제 준비 -> 결제 버튼 누를 때 결제 정보를 받아와야 함.
	@PostMapping
	public PayReadyRes payReady(@RequestHeader("Authorization") String bearerToken){
//	public PayReadyRes payReady(@RequestHeader("Authorization") String bearerToken, @RequestBody PayReadyReq payReadyReq){
		System.out.println("payReady 호출됨");
		// 헤더에 인증 키 + 구매 정보 기입해서 kakaopay ready API 호출
		PayReadyRes payReadyRes = payService.payReady();

		// actionResult.get 을 통해 가져와서 넣어줌

		return payReadyRes;
	}

	@GetMapping("/success")
	public void paySuccess(@RequestParam String pg_token){

	}
}
