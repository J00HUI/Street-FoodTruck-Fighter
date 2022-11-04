package com.ssafy.foodtruck.controller;

import com.ssafy.foodtruck.dto.CurrentOrdersListByFoodtruckResponse;
import com.ssafy.foodtruck.dto.OrdersHistoryResponse;
import com.ssafy.foodtruck.dto.OrdersListByFoodtruckResponse;
import com.ssafy.foodtruck.dto.RegisterOrdersRequest;
import com.ssafy.foodtruck.model.service.OrdersService;
import com.ssafy.foodtruck.model.service.UserService;
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

    private final UserService userService;

    private final JwtTokenUtil jwtTokenUtil;

    private final OrdersService ordersService;

    @PostMapping("/customer")
//    public ResponseEntity<?> registerOrders(@RequestHeader(AUTHORIZATION) String bearerToken, @RequestBody RegisterOrdersRequest registerOrdersRequest) {
    public ResponseEntity<?> registerOrders(@PathVariable int userId, @RequestBody RegisterOrdersRequest registerOrdersRequest) {
        ordersService.registerOrders(userId, registerOrdersRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/ceo/{ordersId}")
    public ResponseEntity<?> acceptOrders(@PathVariable int userId, @PathVariable int ordersId) {
        ordersService.acceptOrders(userId, ordersId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/customer/{userId}")
    public ResponseEntity<List<OrdersHistoryResponse>> getCustomerOrders(@PathVariable int userId) {
        ordersService.getCustomerOrders(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
