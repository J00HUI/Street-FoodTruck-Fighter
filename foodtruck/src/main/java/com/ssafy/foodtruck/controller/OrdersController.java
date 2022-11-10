package com.ssafy.foodtruck.controller;

import com.ssafy.foodtruck.db.entity.User;
import com.ssafy.foodtruck.dto.request.AcceptOrdersReq;
import com.ssafy.foodtruck.dto.request.RegisterOrdersReq;
import com.ssafy.foodtruck.dto.response.*;
import com.ssafy.foodtruck.model.service.OrdersService;
import com.ssafy.foodtruck.model.service.UserService;
import com.ssafy.foodtruck.util.JwtTokenUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ssafy.foodtruck.db.entity.Message.AUTHORIZATION;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrdersController {

	private final OrdersService ordersService;

	private final UserService userService;

	private final JwtTokenUtil jwtTokenUtil;

	@PostMapping("/customer")
	@ApiOperation(value = "주문내역 등록", notes = "<strong>사용자가 주문내역을 등록한다.</strong>")
	public ResponseEntity<?> registerOrders(@RequestHeader(AUTHORIZATION) String bearerToken, @RequestBody RegisterOrdersReq registerOrdersReq) {
		User user = userService.getUserByEmail(jwtTokenUtil.getEmailFromBearerToken(bearerToken));
		ordersService.registerOrders(registerOrdersReq, user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PatchMapping("/ceo/{ordersId}")
	@ApiOperation(value = "Orders ID로 주문 접수", notes = "<strong>Orders ID를 통해 주문을 접수한다.</strong>")
	public ResponseEntity<?> acceptOrders(@RequestHeader(AUTHORIZATION) String bearerToken, @RequestBody AcceptOrdersReq acceptOrdersReq) {
		int ceoId = JwtTokenUtil.getUserIdFromBearerToken(bearerToken);
		ordersService.acceptOrders(ceoId, acceptOrdersReq);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/customer")
	@ApiOperation(value = "현재 주문내역 조회 - 사용자", notes = "<strong>Customer ID를 통해 현재 주문내역 조회를 한다.</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공", response = CurrentOrdersHistoryRes.class),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "사용자 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<List<CurrentOrdersHistoryRes>> getCustomerOrders(@RequestHeader(AUTHORIZATION) String bearerToken) {
		int customerId = JwtTokenUtil.getUserIdFromBearerToken(bearerToken);
		return new ResponseEntity<>(ordersService.getCustomerOrders(customerId), HttpStatus.OK);
	}

	@GetMapping("/customer/all")
	@ApiOperation(value = "전체 주문내역 조회 - 사용자", notes = "<strong>Customer ID를 통해 전체 주문내역 조회를 한다.</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공", response = OrdersHistoryRes.class),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "사용자 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<List<OrdersHistoryRes>> getCustomerOrdersAll(@RequestHeader(AUTHORIZATION) String bearerToken) {
//		int customerId = JwtTokenUtil.getUserIdFromBearerToken(bearerToken);
		User user = userService.getUserByEmail(jwtTokenUtil.getEmailFromBearerToken(bearerToken));
		return new ResponseEntity<>(ordersService.getCustomerOrdersAll(user), HttpStatus.OK);
	}

	@GetMapping("/ceo/not/accepted")
	@ApiOperation(value = "현재 수락되지 않은 주문내역 조회 - 사업자", notes = "<strong>Ceo ID를 통해 주문내역 조회를 한다.</strong>")
	public ResponseEntity<List<CurrentOrdersListByFoodtruckRes>> getCeoOrdersNotAccepted(@RequestHeader(AUTHORIZATION) String bearerToken) {
		User ceoUser = userService.getUserByEmail(jwtTokenUtil.getEmailFromBearerToken(bearerToken));
//		int ceoId = JwtTokenUtil.getUserIdFromBearerToken(bearerToken);
		return new ResponseEntity<>(ordersService.getCeoOrdersNotAccepted(ceoUser), HttpStatus.OK);
	}

	@GetMapping("/ceo/accepted")
	@ApiOperation(value = "현재 수락된 주문내역 조회 - 사업자", notes = "<strong>Ceo ID를 통해 주문내역 조회를 한다.</strong>")
	public ResponseEntity<List<CurrentOrdersListByFoodtruckRes>> getCeoOrdersAccepted(@RequestHeader(AUTHORIZATION) String bearerToken) {
		User ceoUser = userService.getUserByEmail(jwtTokenUtil.getEmailFromBearerToken(bearerToken));
		return new ResponseEntity<>(ordersService.getCeoOrdersAccepted(ceoUser), HttpStatus.OK);
	}

	@GetMapping("/ceo/all")
	@ApiOperation(value = "전체 주문내역 조회 - 사업자", notes = "<strong>Ceo ID를 통해 전체 주문내역 조회를 한다.</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공", response = OrdersListByFoodtruckRes.class),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "사용자 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<List<OrdersListByFoodtruckRes>> getCeoOrdersAll(@RequestHeader(AUTHORIZATION) String bearerToken) {
		int ceoId = JwtTokenUtil.getUserIdFromBearerToken(bearerToken);
		return new ResponseEntity<>(ordersService.getCeoOrdersAll(ceoId), HttpStatus.OK);
	}

	@PatchMapping("/{orderId}")
	@ApiOperation(value = "Orders ID로 주문 취소", notes = "<strong>Orders ID를 통해 주문을 취소한다.</strong>")
	public ResponseEntity<?> cancelOrders(@RequestHeader(AUTHORIZATION) String bearerToken, @PathVariable int orderId) {
		int ceoId = JwtTokenUtil.getUserIdFromBearerToken(bearerToken);
		ordersService.cancelOrders(ceoId, orderId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
