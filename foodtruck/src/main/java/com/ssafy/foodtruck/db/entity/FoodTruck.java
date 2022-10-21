package com.ssafy.foodtruck.db.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class FoodTruck extends BaseEntity {

    @Unique
    @NotNull
    @Column(length = 50)
    private String name;

    @Column(length = 200)
    private String src;

    @Enumerated
    @NotNull
    private Category category;

    @Column(length = 50)
    private String phone;

    @Column(length = 200)
    private String description;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
