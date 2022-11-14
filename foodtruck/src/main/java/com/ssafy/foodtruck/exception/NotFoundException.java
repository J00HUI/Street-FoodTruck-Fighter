package com.ssafy.foodtruck.exception;

import com.ssafy.foodtruck.constant.OrdersErrorMessage;

public class NotFoundException extends RuntimeException {

    public NotFoundException(OrdersErrorMessage message) {
        super(message.getMessage());
    }
}
