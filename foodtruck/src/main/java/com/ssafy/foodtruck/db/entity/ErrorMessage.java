package com.ssafy.foodtruck.db.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {

    FAIL_TO_REGISTER("주문내역 등록에 실패하였습니다.");

    private final String message;
}
