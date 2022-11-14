package com.ssafy.foodtruck.db.entity;

public class Message {

    public static final String AUTHORIZATION = "Authorization";

    public Message(String message) {
        this.message = message;
    }

    public Message(OrdersErrorMessage message) {
        this.message = message.getMessage();
    }

    private String message;
}
