package com.ssafy.foodtruck.db.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Unique
    @NotNull
    @Column(length = 50)
    private String nickname;

    @Unique
    @NotNull
    @Column(length = 50)
    private String email;

    @NotNull
    @Column(length = 50)
    private String password;

    @NotNull
    @Column(length = 50)
    private String phone;

    @Enumerated(EnumType.STRING)
    @NotNull
    private UserType userType;

    @Unique
    @Column(length = 50)
    private String businessNumber;

    @Transient
    private Set<Authority> authorities;
}
