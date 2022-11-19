package com.ssafy.foodtruck.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.foodtruck.dto.ScheduleDateDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetScheduleRes {
	private Integer ScheduleId;

	List<ScheduleDateDto> scheduleDateDtoList;

	private Double latitude;
	private Double longitude;
	private String address;
	private String title;
	private Integer groupId;

//	public void setGetSceduleRes(Integer scheduleId, List<Sc>) {

//	}
}
