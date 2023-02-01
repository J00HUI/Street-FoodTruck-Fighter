package com.ssafy.foodtruck.db.entity;


import com.ssafy.foodtruck.dto.request.UpdateScheduleReq;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Schedule extends BaseEntity {

	@NotNull
	private String title;

	@NotNull
	@Column(name = "group_Id")
	private Integer groupId;

	@NotNull
	private LocalDate workingDate;

	@NotNull
	private LocalDateTime startTime;

	@NotNull
	private LocalDateTime endTime;

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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "foodtruck_id")
	private FoodTruck foodTruck;

	public void setIsValid(boolean isValid) {
		this.isValid = isValid;
	}

	public void update(final UpdateScheduleReq updateScheduleReq) {
		this.startTime = LocalDateTime.parse(this.workingDate + " " + updateScheduleReq.getStartTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		this.endTime = LocalDateTime.parse(this.workingDate + " " + updateScheduleReq.getEndTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		this.latitude = updateScheduleReq.getLat();
		this.longitude = updateScheduleReq.getLng();
		this.address = updateScheduleReq.getAddress();
		this.title = updateScheduleReq.getTitle();
	}
}
