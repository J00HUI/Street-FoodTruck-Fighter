package com.ssafy.foodtruck.dto;


import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String pw;
    private String mobileNumber;
    private String nickname;
}
