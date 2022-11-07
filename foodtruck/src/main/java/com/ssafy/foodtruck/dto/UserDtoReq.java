package com.ssafy.foodtruck.dto;

import com.ssafy.foodtruck.db.entity.UserType;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDtoReq {

    private Long id;
    private String email;
    private String businessNumber;
    private String password;
    private String phone;
    private String nickname;
    private UserType userType;
}
