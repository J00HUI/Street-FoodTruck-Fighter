package com.ssafy.foodtruck.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UpdateScheduleReq {
	private Integer scheduleId; // 스케쥴 Id

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Seoul")
	private String startTime; // 시작 시각

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Seoul")
	private String endTime; // 종료 시각
	private Double lat; // 위도
	private Double lng; // 경도
	private String address; // 주소
	private String title;
}
