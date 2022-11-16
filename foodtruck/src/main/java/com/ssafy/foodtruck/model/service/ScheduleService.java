package com.ssafy.foodtruck.model.service;

import com.ssafy.foodtruck.db.entity.FoodTruck;
import com.ssafy.foodtruck.db.entity.Schedule;
import com.ssafy.foodtruck.db.entity.User;
import com.ssafy.foodtruck.db.repository.FoodtruckRepository;
import com.ssafy.foodtruck.db.repository.ScheduleRepository;
import com.ssafy.foodtruck.dto.ScheduleDateDto;
import com.ssafy.foodtruck.dto.request.CreateScheduleReq;
import com.ssafy.foodtruck.dto.request.UpdateScheduleReq;
import com.ssafy.foodtruck.dto.response.GetScheduleRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

import static com.ssafy.foodtruck.constant.FoodtruckConstant.DUPLICATED_FOODTRUCK_ERROR_MESSAGE;
import static com.ssafy.foodtruck.constant.FoodtruckConstant.NOT_FOUNT_FOODTRUCK_ERROR_MESSAGE;
import static com.ssafy.foodtruck.constant.ScheduleConstant.INCONSISTENCY_ID_SCHEDULE_ERROR_MESSAGE;
import static com.ssafy.foodtruck.constant.ScheduleConstant.NOT_FOUND_SCHEDULE_ERROR_MESSAGE;

@Service("scheduleService")
@RequiredArgsConstructor
public class ScheduleService {

	private final ScheduleRepository scheduleRepository;
	private final FoodtruckRepository foodTruckRepository;

	// 일정 등록
	public void createSchedule(CreateScheduleReq createScheduleReq, User user){
		FoodTruck foodTruck = foodTruckRepository.findByUser(user)
			.orElseThrow(() -> new IllegalArgumentException(NOT_FOUNT_FOODTRUCK_ERROR_MESSAGE));

		Integer groupId = scheduleRepository.findMaxGroupId().orElse(0);
		Integer nowGroupId = groupId + 1;

		for(ScheduleDateDto dateDto : createScheduleReq.getScheduleDateDtoList()){
			final Schedule schedule = Schedule.builder()
				.title(createScheduleReq.getTitle())
				.groupId(nowGroupId)
				.foodTruck(foodTruck)
				.latitude(createScheduleReq.getLatitude())
				.longitude(createScheduleReq.getLongitude())
				.address(createScheduleReq.getAddress())
				.workingDate(LocalDate.parse(dateDto.getWorkingDay(), DateTimeFormatter.ISO_DATE))
				.startTime(LocalDateTime.parse(dateDto.getWorkingDay() + " " + dateDto.getStartTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
				.endTime(LocalDateTime.parse(dateDto.getWorkingDay() + " " + dateDto.getEndTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
				.isValid(true).build();

			scheduleRepository.save(schedule);
		}
	}

	// 일정 수정
	public void updateSchedule(UpdateScheduleReq updateScheduleReq, User user){
		// 일정 푸트트럭 아이디와 user 비교 -> 다르면 수정 불가 (테스트 코트 작성)

		Schedule schedule = scheduleRepository.findById(updateScheduleReq.getScheduleId())
			.orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_SCHEDULE_ERROR_MESSAGE));
		schedule.update(updateScheduleReq);
		scheduleRepository.save(schedule);
	}

	// 일정 취소
	public void cancelSchedule(Integer scheduleId, User user) throws IllegalAccessException {
		// 일정 푸트트럭 아이디와 user 비교 -> 다르면 수정 불가
		Schedule schedule = scheduleRepository.findById(scheduleId)
			.orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_SCHEDULE_ERROR_MESSAGE));
		FoodTruck foodTruck = foodTruckRepository.findByUser(user)
			.orElseThrow(() -> new IllegalArgumentException(NOT_FOUNT_FOODTRUCK_ERROR_MESSAGE));

		if(foodTruck != schedule.getFoodTruck()){
			throw new IllegalAccessException(INCONSISTENCY_ID_SCHEDULE_ERROR_MESSAGE);
		}

		schedule.setIsValid(false);
	}

	public List<GetScheduleRes> getSchedule(User user){
//		LocalDate today = LocalDate.now();
//		LocalDate firstDate = today.withDayOfMonth(1);
//		LocalDate lastDate = today.withDayOfMonth(today.lengthOfMonth());

		FoodTruck foodtruck = foodTruckRepository.findByUser(user)
			.orElseThrow(() -> new IllegalArgumentException(NOT_FOUNT_FOODTRUCK_ERROR_MESSAGE));

//		List<Schedule> findScheduleList = scheduleRepository.findScheduleByFoodTruckAndThisMonth(foodtruck.getId(), firstDate, lastDate);
		List<Schedule> findScheduleList = scheduleRepository.findAllByFoodTruck(foodtruck);

		List<GetScheduleRes> scheduleResList = new ArrayList<>();
		for(Schedule schedule : findScheduleList){
			scheduleResList.add(GetScheduleRes.builder()
				.ScheduleId(schedule.getId())
				.workingDate(schedule.getWorkingDate())
				.startTime(schedule.getStartTime())
				.endTime(schedule.getEndTime())
				.latitude(schedule.getLatitude())
				.longitude(schedule.getLongitude())
				.address(schedule.getAddress())
				.title(schedule.getTitle())
				.groupId(schedule.getGroupId())
				.build());
		}

		return scheduleResList;
	}
}
