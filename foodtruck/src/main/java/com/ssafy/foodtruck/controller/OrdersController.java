package com.ssafy.foodtruck.controller;

import com.ssafy.foodtruck.dto.*;
import com.ssafy.foodtruck.model.service.OrdersService;
import com.ssafy.foodtruck.util.JwtTokenUtil;
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

	@PostMapping("/customer")
	public ResponseEntity<?> registerOrders(@RequestHeader(AUTHORIZATION) String bearerToken, @RequestBody RegisterOrdersRequest registerOrdersRequest) {
		int customerId = JwtTokenUtil.getUserIdFromBearerToken(bearerToken);
		ordersService.registerOrders(customerId, registerOrdersRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PatchMapping("/ceo/{ordersId}")
	public ResponseEntity<?> acceptOrders(@RequestHeader(AUTHORIZATION) String bearerToken, @PathVariable int ordersId) {
		int ceoId = JwtTokenUtil.getUserIdFromBearerToken(bearerToken);
		ordersService.acceptOrders(ceoId, ordersId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/customer/{userId}")
	public ResponseEntity<List<CurrentOrdersHistoryResponse>> getCustomerOrders(@RequestHeader(AUTHORIZATION) String bearerToken) {
		int customerId = JwtTokenUtil.getUserIdFromBearerToken(bearerToken);
		return new ResponseEntity<>(ordersService.getCustomerOrders(customerId), HttpStatus.OK);
	}

	@GetMapping("/customer/all")
	public ResponseEntity<List<OrdersHistoryResponse>> getCustomerOrdersAll(@RequestHeader(AUTHORIZATION) String bearerToken) {
		int customerId = JwtTokenUtil.getUserIdFromBearerToken(bearerToken);
		return new ResponseEntity<>(ordersService.getCustomerOrdersAll(customerId), HttpStatus.OK);
	}

    @GetMapping("/ceo")
    public ResponseEntity<List<CurrentOrdersListByFoodtruckResponse>> getCeoOrders(@PathVariable int userId, @PathVariable int foodtruckId) {
//        ordersService.getCeoOrders(userService.getUserByEmail(jwtTokenUtil.getEmailFromBearerToken(bearerToken)));
        ordersService.getCeoOrders(userId, foodtruckId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/ceo/all")
    public ResponseEntity<List<OrdersListByFoodtruckResponse>> getCeoOrdersAll(@PathVariable int userId, @PathVariable int foodtruckId) {
        ordersService.getCeoOrdersAll(userId, foodtruckId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<?> cancelOrders(@PathVariable int userId, @PathVariable int orderId) {
        ordersService.cancelOrders(userId, orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
