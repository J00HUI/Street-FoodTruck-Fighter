package com.ssafy.foodtruck.db.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Survey extends BaseEntity {

    @NotNull
    @Column(length = 200)
    private Category category;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @NotNull
    @Column(length = 200)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
