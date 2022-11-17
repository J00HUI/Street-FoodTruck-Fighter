package com.ssafy.foodtruck.controller;

import com.ssafy.foodtruck.db.entity.User;
import com.ssafy.foodtruck.dto.PayApprovalDto;
import com.ssafy.foodtruck.dto.request.PayReadyReq;
import com.ssafy.foodtruck.dto.request.RegisterOrdersReq;
import com.ssafy.foodtruck.dto.response.PayApprovalRes;
import com.ssafy.foodtruck.dto.response.PayReadyRes;
import com.ssafy.foodtruck.dto.response.RegisterOrdersRes;
import com.ssafy.foodtruck.exception.NotFoundException;
import com.ssafy.foodtruck.model.service.OrdersService;
import com.ssafy.foodtruck.model.service.PayService;
import com.ssafy.foodtruck.model.service.UserService;
import com.ssafy.foodtruck.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 카카오페이 관련 API 요청 처리를 위한 컨트롤러 정의
 */
@RestController
@RequestMapping("/v1/pay")
@RequiredArgsConstructor
@Api(value = "결제 API", tags = {"Pay"})
public class PayController {

	private final OrdersService ordersService;

	private final UserService userService;

	private final PayService payService;

	private final JwtTokenUtil jwtTokenUtil;

	PayApprovalDto payApprovalDto;

	// 결제 준비 -> 결제 버튼 누를 때 결제 정보를 받아와야 함.
	@PostMapping
	public ResponseEntity<?> payReady(@RequestHeader("Authorization") String bearerToken, @RequestBody RegisterOrdersReq registerOrdersReq){
		System.out.println("payReady 호출됨");

		User user = userService.getUserByEmail(jwtTokenUtil.getEmailFromBearerToken(bearerToken));
		try {
			// 결제 요청 시 주문 테이블에 주문 내역 업로드
			RegisterOrdersRes registerOrdersRes = ordersService.registerOrders(registerOrdersReq, user);
			// 헤더에 인증 키 + 구매 정보 기입해서 kakaopay ready API 호출
			PayReadyRes payReadyRes = payService.payReady(registerOrdersRes);

			// 승인 요청 받을 때 필요한 데이터
			payApprovalDto = new PayApprovalDto(
				"TC0ONETIME",
				payReadyRes.getTid(),
				String.valueOf(registerOrdersRes.getOrders().getId()),
				String.valueOf(registerOrdersRes.getOrders().getUser().getId())
			);

			return new ResponseEntity<>(payReadyRes, HttpStatus.OK);

		} catch (NotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/success")
	public ResponseEntity<PayApprovalRes> paySuccess(@RequestParam String pg_token){
		System.out.println("token: " + pg_token);
		ResponseEntity<PayApprovalRes> payApprovalRes = payService.paySuccess(payApprovalDto, pg_token);

		// 결제 완료 설정
		ordersService.successPay(Integer.parseInt(payApprovalDto.getPartner_order_id()));

		return payApprovalRes;
	}
}
