package com.ssafy.foodtruck.dto.request;

import com.ssafy.foodtruck.dto.ScheduleDateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateScheduleReq {
	List<ScheduleDateDto> scheduleDateDtoList;
	private Double latitude;
	private Double longtitude;
	private String address;
}
