package com.ssafy.foodtruck.controller;

import com.ssafy.foodtruck.db.entity.User;
import com.ssafy.foodtruck.db.repository.UserRepository;
import com.ssafy.foodtruck.dto.request.AcceptOrdersReq;
import com.ssafy.foodtruck.dto.request.RegisterOrdersReq;
import com.ssafy.foodtruck.dto.response.*;
import com.ssafy.foodtruck.exception.NotFoundException;
import com.ssafy.foodtruck.model.service.OrdersService;
import com.ssafy.foodtruck.model.service.UserService;
import com.ssafy.foodtruck.util.JwtTokenUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import static com.ssafy.foodtruck.constant.OrdersConsatnt.CANCELED_ORDERS_SUCCESS;
import static com.ssafy.foodtruck.db.entity.Message.AUTHORIZATION;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrdersController {

	private final UserRepository userRepository;
	private final OrdersService ordersService;
	private final UserService userService;
	private final JwtTokenUtil jwtTokenUtil;

	@PostMapping("/customer")
	@ApiOperation(value = "주문내역 등록", notes = "<strong>사용자가 주문내역을 등록한다.</strong>")
	public ResponseEntity<?> registerOrders(@RequestHeader(AUTHORIZATION) @ApiParam(value="Access Token", required = true) String bearerToken,
											@RequestBody @ApiParam(value="주문내역 정보", required = true) RegisterOrdersReq registerOrdersReq) {
		User user = userService.getUserByEmail(jwtTokenUtil.getEmailFromBearerToken(bearerToken));
		try {
			ordersService.registerOrders(registerOrdersReq, user);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PatchMapping("/ceo/accept")
	@ApiOperation(value = "주문 접수 - 사업자", notes = "<strong>Orders ID와 주문 완료 예상 시간을 통해 주문을 접수한다.</strong>")
	public ResponseEntity<?> acceptOrders(@RequestHeader(AUTHORIZATION) @ApiParam(value="Access Token", required = true) String bearerToken,
										  @RequestBody @ApiParam(value="주문내역 정보", required = true) AcceptOrdersReq acceptOrdersReq) {
		int ceoId = JwtTokenUtil.getUserIdFromBearerToken(bearerToken);
		try {
			ordersService.acceptOrders(ceoId, acceptOrdersReq);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/customer")
	@ApiOperation(value = "현재 주문내역 조회 - 사용자", notes = "<strong>Customer ID를 통해 현재 주문내역 조회를 한다.</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공", response = CurrentOrdersHistoryRes.class),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "사용자 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<List<CurrentOrdersHistoryRes>> getCustomerOrders(@RequestHeader(AUTHORIZATION) @ApiParam(value="Access Token", required = true) String bearerToken) {
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
	public ResponseEntity<List<OrdersHistoryAllRes>> getCustomerOrdersAll(@RequestHeader(AUTHORIZATION) @ApiParam(value="Access Token", required = true) String bearerToken) {
//		int customerId = JwtTokenUtil.getUserIdFromBearerToken(bearerToken);
		User user = userService.getUserByEmail(jwtTokenUtil.getEmailFromBearerToken(bearerToken));
		return new ResponseEntity<>(ordersService.getCustomerOrdersAll(user), HttpStatus.OK);
	}


	@GetMapping(path = "/ceo/not/accepted/{ceo_id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	@ApiOperation(value = "현재 수락되지 않은 주문내역 조회 - 사업자", notes = "<strong>Ceo ID를 통해 주문내역 조회를 한다.</strong>")
	public ResponseEntity<?> getCeoOrdersNotAccepted(@PathVariable("ceo_id") int ceoId) {


		Optional<User> ceoUserOpt = userRepository.findById(ceoId);

		if(!ceoUserOpt.isPresent()){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		User ceoUser = ceoUserOpt.get();
		List<CurrentOrdersListByFoodtruckRes> currentOrdersListByFoodtruckResList = ordersService.getCeoOrdersNotAccepted(ceoUser);

		if(currentOrdersListByFoodtruckResList.size() == 0) {
			return new ResponseEntity<>("수락되지 않은 주문내역이 없습니다.", HttpStatus.OK);
		}
		return new ResponseEntity<>(currentOrdersListByFoodtruckResList, HttpStatus.OK);
	}

	//SSE
	@GetMapping("/ceo/accepted/{ceo_id}")
	@ApiOperation(value = "현재 수락된 주문내역 조회 - 사업자 (SSE)", notes = "<strong>Ceo ID를 통해 주문내역 조회를 한다.</strong>")
	public Flux<?> getCeoOrdersAccepted(@PathVariable("ceo_id") int ceoId) {
		Optional<User> ceoUserOpt = userRepository.findById(ceoId);

		if (!ceoUserOpt.isPresent()) {
			return Flux.interval(Duration.ofHours(1))
				.map(sequence -> "적절하지 못한 ceoId입니다.");
		}

		User ceoUser = ceoUserOpt.get();
		try {
			List<CurrentOrdersListByFoodtruckRes> currentOrdersListByFoodtruckResList = ordersService.getCeoOrdersAccepted(ceoUser);
			return Flux.interval(Duration.ofSeconds(1))
				.map(sequence -> currentOrdersListByFoodtruckResList);
		} catch (NotFoundException ex) {
			return Flux.interval(Duration.ofHours(1))
				.map(sequence -> "적절하지 못한 ceoId입니다.");
		}
	}

//	@GetMapping("/ceo/all")
//	@ApiOperation(value = "전체 주문내역 조회 - 사업자", notes = "<strong>Ceo ID를 통해 전체 주문내역 조회를 한다.</strong>")
//	@ApiResponses({
//		@ApiResponse(code = 200, message = "성공", response = OrdersListByFoodtruckRes.class),
//		@ApiResponse(code = 401, message = "인증 실패"),
//		@ApiResponse(code = 404, message = "사용자 없음"),
//		@ApiResponse(code = 500, message = "서버 오류")
//	})
//	public ResponseEntity<?> getCeoOrdersAll(@RequestHeader(AUTHORIZATION) String bearerToken) {
//		int ceoId = JwtTokenUtil.getUserIdFromBearerToken(bearerToken);
//		try {
//			List<OrdersListByFoodtruckRes> ordersListByFoodtruckResList = ordersService.getCeoOrdersAll(ceoId);
//			return new ResponseEntity<>(ordersListByFoodtruckResList, HttpStatus.OK);
//		} catch (NotFoundException ex) {
//			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//		}
//	}


	@PatchMapping("/cancel/{orderId}")
	@ApiOperation(value = "Orders ID로 주문 취소 - 사업자", notes = "<strong>Orders ID를 통해 주문을 취소한다.</strong>")
	public ResponseEntity<?> cancelOrders(@RequestHeader(AUTHORIZATION) @ApiParam(value="Access Token", required = true) String bearerToken,
										  @PathVariable int orderId) {
		int ceoId = JwtTokenUtil.getUserIdFromBearerToken(bearerToken);
		try {
			ordersService.cancelOrders(ceoId, orderId);
			return new ResponseEntity<>(CANCELED_ORDERS_SUCCESS, HttpStatus.OK);
		} catch (NotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PatchMapping("/done/{orderId}")
	@ApiOperation(value = "주문 완료 - 사업자", notes = "<strong>Orders ID를 통해 주문을 완료한다.</strong>")
	public ResponseEntity<?> doneOrders(@RequestHeader(AUTHORIZATION) @ApiParam(value="Access Token", required = true) String bearerToken,
										@PathVariable @ApiParam(value="주문 ID", required = true) int orderId) {
		int ceoId = JwtTokenUtil.getUserIdFromBearerToken(bearerToken);

		try {
			ordersService.doneOrders(ceoId, orderId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
