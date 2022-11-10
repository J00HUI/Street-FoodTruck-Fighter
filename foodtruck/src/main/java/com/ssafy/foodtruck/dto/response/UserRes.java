package com.ssafy.foodtruck.dto.response;

import com.ssafy.foodtruck.db.entity.UserType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class UserRes {

    private Integer id;
//    private String businessNumber;
    private String email;
    private String nickname;
    private String phone;
    private UserType userType;
}
