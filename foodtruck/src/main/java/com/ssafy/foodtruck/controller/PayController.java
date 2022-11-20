package com.ssafy.foodtruck.controller;

import com.ssafy.foodtruck.db.entity.User;
import com.ssafy.foodtruck.dto.PayApprovalDto;
import com.ssafy.foodtruck.dto.request.PayApprovalReq;
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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
	@ApiOperation(value = "결제 준비 요청", notes = "<strong>주문내역 정보를 통해 카카오 Pay API에 결제 준비 요청을 한다.</strong>")
	public ResponseEntity<?> payReady(@RequestHeader("Authorization") @ApiParam(value="Access Token", required = true) String bearerToken,
									  @RequestBody @ApiParam(value="주문내역 정보", required = true) RegisterOrdersReq registerOrdersReq){
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

			payReadyRes.setPartner_order_id(String.valueOf(registerOrdersRes.getOrders().getId()));
			payReadyRes.setPartner_user_id(String.valueOf(registerOrdersRes.getOrders().getUser().getId()));

			return new ResponseEntity<>(payReadyRes, HttpStatus.OK);

		} catch (NotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/success/{pg_token}")
	@ApiOperation(value = "결제 승인 요청", notes = "<strong>토큰을 통해 카카오 Pay API에 결제 승인 요청을 한다.</strong>")
	public ResponseEntity<PayApprovalRes> paySuccess(@PathVariable("pg_token") @ApiParam(value="결제 승인 요청 토큰", required = true) String pg_token, @RequestBody @ApiParam(value="결제 승인 요청", required = true) PayApprovalReq payApprovalReq){
		System.out.println("token: " + pg_token);
		System.out.println("tid : " + payApprovalReq.getTid());
		ResponseEntity<PayApprovalRes> payApprovalRes = payService.paySuccess(payApprovalReq, pg_token);

		// 결제 완료 설정
		ordersService.successPay(Integer.parseInt(payApprovalDto.getPartner_order_id()));

		return payApprovalRes;
	}
}
