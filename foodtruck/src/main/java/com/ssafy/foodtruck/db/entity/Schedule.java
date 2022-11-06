package com.ssafy.foodtruck.db.entity;


import com.ssafy.foodtruck.dto.request.RegisterFoodTruckReq;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    @NotNull
    @ColumnDefault("true")
    private Boolean isValid;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @NotNull
    @Column(length = 200)
    private String address;

    @ManyToOne
    @JoinColumn(name = "foodtruck_id")
    private FoodTruck foodTruck;

	public void update(final RegisterFoodTruckReq registerFoodTruckReq){
		this.startDate = LocalDateTime.parse(registerFoodTruckReq.getStart_date(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		this.endDate = LocalDateTime.parse(registerFoodTruckReq.getEnd_date(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		this.latitude = registerFoodTruckReq.getLatitude();
		this.longitude = registerFoodTruckReq.getLongtitue();
		this.address = registerFoodTruckReq.getAddress();
	}
}
