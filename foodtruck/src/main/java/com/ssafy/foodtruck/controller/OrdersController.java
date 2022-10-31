package com.ssafy.foodtruck.controller;

import com.ssafy.foodtruck.dto.RegisterOrdersRequest;
import com.ssafy.foodtruck.model.service.OrdersService;
import com.ssafy.foodtruck.model.service.UserService;
import com.ssafy.foodtruck.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ssafy.foodtruck.db.entity.Message.AUTHORIZATION;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/order")
public class OrdersController {

    private final UserService userService;

    private final JwtTokenUtil jwtTokenUtil;

    private final OrdersService ordersService;

    @PostMapping("/customer")
    public ResponseEntity<?> registerOrders(@RequestHeader(AUTHORIZATION) String bearerToken, @RequestBody RegisterOrdersRequest registerOrdersRequest) {
        ordersService.registerOrders(userService.getUserByEmail(jwtTokenUtil.getEmailFromBearerToken(bearerToken)), registerOrdersRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
