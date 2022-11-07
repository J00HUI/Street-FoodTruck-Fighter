package com.ssafy.foodtruck.controller;

import com.ssafy.foodtruck.db.entity.User;
import com.ssafy.foodtruck.dto.request.CreateScheduleReq;
import com.ssafy.foodtruck.dto.request.RegisterFoodTruckReq;
import com.ssafy.foodtruck.model.service.ScheduleService;
import com.ssafy.foodtruck.model.service.UserService;
import com.ssafy.foodtruck.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 스케줄 관련 API 요청 처리를 위한 컨트롤러 정의
 */
@Api(value = "스케줄 API", tags = {"Schedule"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {

	private final ScheduleService scheduleService;

	private final UserService userService;

	private final JwtTokenUtil jwtTokenUtil;

	// 일정 등록
	@PostMapping
	@ApiOperation(value = "일정 등록", notes = "<strong>새로운 일정을 등록한다.</strong>")
	public void createSchedule(@RequestHeader("Authorization") @ApiParam(value="Access Token", required = true) String bearerToken, @RequestBody @ApiParam(value="일정 정보", required = true) CreateScheduleReq createScheduleReq){
		User user = userService.getUserByEmail(jwtTokenUtil.getEmailFromBearerToken(bearerToken));
		scheduleService.createSchedule(createScheduleReq, user);
	}

	// 일정 수정

	// 일정 취소

	// 한달간 일정 조회
}
