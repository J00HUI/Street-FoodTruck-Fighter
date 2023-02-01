package com.ssafy.foodtruck.dto.request;

import com.ssafy.foodtruck.db.entity.UserType;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class UserReq {

    private String email;
    private String businessNumber;
    private String password;
    private String phone;
    private String nickname;
    private UserType userType;
}
