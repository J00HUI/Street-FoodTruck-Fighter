package com.ssafy.foodtruck.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String email;
    private String password;
    private String mobileNumber;
    private String nickname;
}
