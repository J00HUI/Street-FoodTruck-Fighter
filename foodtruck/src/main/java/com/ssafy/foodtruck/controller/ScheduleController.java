package com.ssafy.foodtruck.controller;

import com.ssafy.foodtruck.common.Response;
import com.ssafy.foodtruck.db.entity.User;
import com.ssafy.foodtruck.dto.request.CreateScheduleReq;
import com.ssafy.foodtruck.dto.request.RegisterFoodTruckReq;
import com.ssafy.foodtruck.dto.request.UpdateScheduleReq;
import com.ssafy.foodtruck.dto.response.GetScheduleRes;
import com.ssafy.foodtruck.dto.response.OrdersListByFoodtruckRes;
import com.ssafy.foodtruck.model.service.ScheduleService;
import com.ssafy.foodtruck.model.service.UserService;
import com.ssafy.foodtruck.util.JwtTokenUtil;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ssafy.foodtruck.constant.FoodTruckConstant.GET_FOODTRUCK_SUCCESS;
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
		scheduleService.createSchedule(createScheduleReq, user);
		return ResponseEntity.ok().body(CREATE_SCHEDULE_SUCCESS);
	}

	// 일정 수정
	@PatchMapping
	@ApiOperation(value = "일정 수정", notes = "<strong>일정을 수정한다.</strong>")
	public ResponseEntity<?> updateSchedule(@RequestHeader("Authorization") @ApiParam(value="Access Token", required = true) String bearerToken, @RequestBody @ApiParam(value="일정 정보", required = true) UpdateScheduleReq updateScheduleReq){
		User user = userService.getUserByEmail(jwtTokenUtil.getEmailFromBearerToken(bearerToken));
		scheduleService.updateSchedule(updateScheduleReq, user);
		return ResponseEntity.ok().body(UPDATE_SCHEDULE_SUCCESS);
	}
	// 일정 취소
	@PatchMapping("/{schedule_id}")
	@ApiOperation(value = "일정 취소", notes = "<strong>일정을 취소한다.</strong>")
	public ResponseEntity<?> cancleSchedule(@RequestHeader("Authorization") @ApiParam(value="Access Token", required = true) String bearerToken, @PathVariable("schedule_id") @ApiParam(value="스케쥴 ID", required = true) Integer scheduleId){
		User user = userService.getUserByEmail(jwtTokenUtil.getEmailFromBearerToken(bearerToken));
		scheduleService.cancelSchedule(scheduleId, user);
		return ResponseEntity.ok().body(CANCEL_SCHEDULE_SUCCESS);
	}

	// 한달간 일정 조회
	@GetMapping("/all")
	@ApiOperation(value = "일정 조회", notes = "<strong>이번달 일정을 조회한다.</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공", response = GetScheduleRes.class),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "사용자 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<Map<String, Object>> getSchedule(@RequestHeader("Authorization") @ApiParam(value="Access Token", required = true) String bearerToken){
		User user = userService.getUserByEmail(jwtTokenUtil.getEmailFromBearerToken(bearerToken));
		List<GetScheduleRes> scheduleResList = scheduleService.getSchedule(user);

		Map<String, Object> result = new HashMap<>();
		result.put("data", scheduleResList);
		result.put("msg", GET_SCHEDULE_SUCCESS);
		return ResponseEntity.ok().body(result);
	}
}
