package com.ssafy.foodtruck.controller;

import com.ssafy.foodtruck.db.entity.User;
import com.ssafy.foodtruck.dto.request.CreateScheduleReq;
import com.ssafy.foodtruck.dto.request.UpdateScheduleReq;
import com.ssafy.foodtruck.dto.response.GetScheduleRes;
import com.ssafy.foodtruck.model.service.ScheduleService;
import com.ssafy.foodtruck.model.service.UserService;
import com.ssafy.foodtruck.util.JwtTokenUtil;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ssafy.foodtruck.constant.ScheduleConstant.*;

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
	public ResponseEntity<?> createSchedule(@RequestHeader("Authorization") @ApiParam(value="Access Token", required = true) String bearerToken, @RequestBody @ApiParam(value="일정 정보", required = true) CreateScheduleReq createScheduleReq){
		User user = userService.getUserByEmail(jwtTokenUtil.getEmailFromBearerToken(bearerToken));
		try {
			scheduleService.createSchedule(createScheduleReq, user);
			return new ResponseEntity<>(CREATE_SCHEDULE_SUCCESS, HttpStatus.CREATED);
		} catch (IllegalArgumentException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// 일정 수정
	@PatchMapping
	@ApiOperation(value = "일정 수정", notes = "<strong>일정을 수정한다.</strong>")
	public ResponseEntity<?> updateSchedule(@RequestHeader("Authorization") @ApiParam(value="Access Token", required = true) String bearerToken, @RequestBody @ApiParam(value="일정 정보", required = true) UpdateScheduleReq updateScheduleReq){
		User user = userService.getUserByEmail(jwtTokenUtil.getEmailFromBearerToken(bearerToken));
		try {
			scheduleService.updateSchedule(updateScheduleReq, user);
		} catch (IllegalArgumentException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(UPDATE_SCHEDULE_SUCCESS, HttpStatus.OK);
	}

	// 일정 취소
	@PatchMapping("/{schedule_id}")
	@ApiOperation(value = "일정 취소", notes = "<strong>일정을 취소한다.</strong>")
	public ResponseEntity<?> cancleSchedule(@RequestHeader("Authorization") @ApiParam(value="Access Token", required = true) String bearerToken, @PathVariable("schedule_id") @ApiParam(value="스케쥴 ID", required = true) Integer scheduleId){
		User user = userService.getUserByEmail(jwtTokenUtil.getEmailFromBearerToken(bearerToken));
		try {
			scheduleService.cancelSchedule(scheduleId, user);
			return new ResponseEntity<>(CANCEL_SCHEDULE_SUCCESS, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping()
	@ApiOperation(value = "내 다음 일정 조회", notes = "<strong>내 다음 일정을 조회한다.</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공", response = GetScheduleRes.class),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "사용자 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<?> getNextSchedule(@RequestHeader("Authorization") @ApiParam(value="Access Token", required = true) String bearerToken) {
		User user = userService.getUserByEmail(jwtTokenUtil.getEmailFromBearerToken(bearerToken));
		try {
			GetScheduleRes getScheduleRes = scheduleService.getNextSchedule(user);
			return new ResponseEntity<>(getScheduleRes, HttpStatus.OK);
		} catch (IllegalArgumentException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/all")
	@ApiOperation(value = "일정 조회", notes = "<strong>일정을 조회한다.</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공", response = GetScheduleRes.class),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "사용자 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<?> getSchedule(@RequestHeader("Authorization") @ApiParam(value="Access Token", required = true) String bearerToken) {
		User user = userService.getUserByEmail(jwtTokenUtil.getEmailFromBearerToken(bearerToken));
		try {
			List<GetScheduleRes> scheduleResList = scheduleService.getSchedule(user);
			return new ResponseEntity<>(scheduleResList, HttpStatus.OK);
		} catch (IllegalArgumentException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
