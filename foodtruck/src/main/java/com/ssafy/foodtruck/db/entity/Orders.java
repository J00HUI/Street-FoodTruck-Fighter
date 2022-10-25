package com.ssafy.foodtruck.db.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders extends BaseEntity {

    @NotNull
    @ColumnDefault("false")
    private Boolean isDone;

    @NotNull
    @ColumnDefault("false")
    private Boolean isAccepted;

    @NotNull
    @ColumnDefault("false")
    private Boolean isCanceled;

    private LocalDateTime doneDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "foodtruck_id")
    private FoodTruck foodTruck;
}
