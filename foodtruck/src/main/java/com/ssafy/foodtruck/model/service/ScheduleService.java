package com.ssafy.foodtruck.model.service;

import com.ssafy.foodtruck.db.entity.FoodTruck;
import com.ssafy.foodtruck.db.entity.Schedule;
import com.ssafy.foodtruck.db.entity.User;
import com.ssafy.foodtruck.db.repository.FoodTruckRepository;
import com.ssafy.foodtruck.db.repository.ScheduleRepository;
import com.ssafy.foodtruck.dto.ScheduleDateDto;
import com.ssafy.foodtruck.dto.request.CreateScheduleReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.ssafy.foodtruck.constant.FoodTruckConstant.NOT_FOUNT_FOODTRUCK_ERROR_MESSAGE;

@Service("scheduleService")
@RequiredArgsConstructor
public class ScheduleService {

	private final ScheduleRepository scheduleRepository;
	private final FoodTruckRepository foodTruckRepository;

	// 일정 등록
	public void createSchedule(CreateScheduleReq createScheduleReq, User user){
		FoodTruck foodTruck = foodTruckRepository.findByUser(user)
			.orElseThrow(() -> new IllegalArgumentException(NOT_FOUNT_FOODTRUCK_ERROR_MESSAGE));

		for(ScheduleDateDto dateDto : createScheduleReq.getScheduleDateDtoList()){
			// 일정이 중복되게 등록되면 안됨 -> 테스트 코드 작성
			final Schedule schedule = Schedule.builder()
				.foodTruck(foodTruck)
				.latitude(createScheduleReq.getLatitude())
				.longitude(createScheduleReq.getLongtitude())
				.address(createScheduleReq.getAddress())
				.workingDate(LocalDate.parse(dateDto.getWorkingDay(), DateTimeFormatter.ISO_DATE))
				.startTime(LocalDateTime.parse(dateDto.getWorkingDay() + " " + dateDto.getStartTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
				.endTime(LocalDateTime.parse(dateDto.getWorkingDay() + " " + dateDto.getEndTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
				.isValid(true).build();

			scheduleRepository.save(schedule);
		}
	}
}
