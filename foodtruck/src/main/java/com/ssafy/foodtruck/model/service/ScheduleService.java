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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	@Transactional
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
		List<Schedule> findScheduleList = scheduleRepository.findAllByFoodtruck(foodtruck.getId());

		List<GetScheduleRes> scheduleResList = new ArrayList<>();
		List<ScheduleDateDto> scheduleDateDtoList = new ArrayList<>();

		if(!findScheduleList.isEmpty()) {
			Integer groupId = findScheduleList.get(0).getGroupId();

			for(int i=0; i<findScheduleList.size(); i++){
				Schedule schedule = findScheduleList.get(i);

				if(groupId == schedule.getGroupId()) {
					scheduleDateDtoList.add(ScheduleDateDto.builder()
						.workingDay(schedule.getWorkingDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
						.startTime(schedule.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm")))
						.endTime(schedule.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm")))
						.build());
				} else {
					// GroupId 가 달라지면 이전 스케줄 리스트 가져와서 add
					Schedule newSchedule = findScheduleList.get(i - 1);
					List<ScheduleDateDto> input = new ArrayList<>();
					input.addAll(scheduleDateDtoList);
					GetScheduleRes getScheduleRes = GetScheduleRes.builder()
						.ScheduleId(newSchedule.getId())
						.latitude(newSchedule.getLatitude())
						.longitude(newSchedule.getLongitude())
						.address(newSchedule.getAddress())
						.title(newSchedule.getTitle())
						.groupId(newSchedule.getGroupId()).build();
					getScheduleRes.setScheduleDateDtoList(input);

					scheduleResList.add(getScheduleRes);

					groupId = schedule.getGroupId();
					scheduleDateDtoList.clear();

					scheduleDateDtoList.add(ScheduleDateDto.builder()
						.workingDay(schedule.getWorkingDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
						.startTime(schedule.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm")))
						.endTime(schedule.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm")))
						.build());

				}
			}

			// 마지막 스케줄 add
			Schedule lastSchedule = findScheduleList.get(findScheduleList.size()-1);
			scheduleResList.add(GetScheduleRes.builder()
				.ScheduleId(lastSchedule.getId())
				.latitude(lastSchedule.getLatitude())
				.longitude(lastSchedule.getLongitude())
				.address(lastSchedule.getAddress())
				.title(lastSchedule.getTitle())
				.scheduleDateDtoList(scheduleDateDtoList)
				.groupId(lastSchedule.getGroupId()).build());
		}

		return scheduleResList;
	}

	public GetScheduleRes getNextSchedule(User user) {
		Optional<FoodTruck> foodTruckOpt = foodTruckRepository.findByUser(user);
		if(!foodTruckOpt.isPresent()){
			throw new RuntimeException("등록된 푸드트럭이 없습니다.");
		}

		Optional<Schedule> nextSchedule = scheduleRepository.findNextSchedule(foodTruckOpt.get().getId());
		if(!nextSchedule.isPresent()){
			throw new RuntimeException("다음 스케줄이 없습니다.");
		}

		return GetScheduleRes.oof(nextSchedule.get());
	}
}
