package com.ssafy.foodtruck.exception;

import com.ssafy.foodtruck.db.entity.OrdersErrorMessage;

public class NotFoundException extends RuntimeException {

    public NotFoundException(OrdersErrorMessage message) {
        super(message.getMessage());
    }
}
