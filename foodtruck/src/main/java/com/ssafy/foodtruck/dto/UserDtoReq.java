package com.ssafy.foodtruck.dto;

import com.ssafy.foodtruck.db.entity.Authority;
import com.ssafy.foodtruck.db.entity.UserType;
import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDtoReq {

    private Integer id;
    private String email;
    private String businessNumber;
    private String password;
    private String phone;
    private String nickname;
    private UserType userType;
    private Set<Authority> authorities;

}
