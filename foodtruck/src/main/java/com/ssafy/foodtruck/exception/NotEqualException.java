package com.ssafy.foodtruck.exception;

import com.ssafy.foodtruck.db.entity.OrdersErrorMessage;

public class NotEqualException extends RuntimeException {

	public NotEqualException(OrdersErrorMessage message) {
		super(message.getMessage());
	}
}
