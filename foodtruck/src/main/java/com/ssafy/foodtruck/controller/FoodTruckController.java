package com.ssafy.foodtruck.controller;

import com.ssafy.foodtruck.common.Response;
import com.ssafy.foodtruck.db.entity.FoodTruck;
import com.ssafy.foodtruck.db.entity.User;
import com.ssafy.foodtruck.dto.request.GetNearFoodTruckReq;
import com.ssafy.foodtruck.dto.request.RegisterFoodTruckReq;
import com.ssafy.foodtruck.dto.request.RegisterFoodTruckReviewReq;
import com.ssafy.foodtruck.dto.response.GetFoodTruckRes;
import com.ssafy.foodtruck.dto.response.GetFoodTruckReviewRes;
import com.ssafy.foodtruck.dto.response.GetNearFoodTruckRes;
import com.ssafy.foodtruck.exception.NotFoundException;
import com.ssafy.foodtruck.model.service.FoodTruckService;
import com.ssafy.foodtruck.model.service.UserService;
import com.ssafy.foodtruck.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ssafy.foodtruck.constant.FoodTruckConstant.*;
import java.util.*;

/**
 * 푸드트럭 관련 API 요청 처리를 위한 컨트롤러 정의
 */
@Api(value = "푸드트럭 API", tags = {"FoodTruck"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/foodtruck")
public class FoodTruckController {

	private final FoodTruckService foodTruckService;

	private final UserService userService;

	private final JwtTokenUtil jwtTokenUtil;

	@GetMapping
	@ApiOperation(value = "본인 푸드트럭 조회", notes = "<strong>본인 푸드트럭 정보를 조회한다.</strong>")
	public ResponseEntity<FoodTruck> getFoodTruck(@RequestHeader("Authorization") String bearerToken) {
		User user = userService.getUserByEmail(jwtTokenUtil.getEmailFromBearerToken(bearerToken));
		return new ResponseEntity<>(foodTruckService.getFoodTruckByUser(user), HttpStatus.OK);
	}

	// 푸드트럭 정보를 가져옴
	@GetMapping("/{foodtruck_id}")
	@ApiOperation(value = "푸드트럭 ID 로 푸드트럭 조회", notes = "<strong>푸드트럭 ID 를 통해 푸드트럭 정보를 조회한다.</strong>")
	public ResponseEntity<Map<String, Object>> getFoodTruck(@PathVariable("foodtruck_id") @ApiParam(value="푸드트럭 ID", required = true) Integer foodTruckId){
		GetFoodTruckRes getFoodTruckRes = foodTruckService.getFoodTruck(foodTruckId);
		Map<String, Object> result = new HashMap<>();
		result.put("data", getFoodTruckRes);
		result.put("msg", GET_FOODTRUCK_SUCCESS);
		return ResponseEntity.ok().body(result);
	}

	// 푸드트럭 등록
	@PostMapping()
	@ApiOperation(value = "푸드트럭 등록", notes = "<strong>내 푸드트럭을 등록한다.</strong>")
	public ResponseEntity<?> registerFoodTruck(@RequestHeader("Authorization") @ApiParam(value="Access Token", required = true) String bearerToken, @RequestBody @ApiParam(value="푸드트럭 정보", required = true) RegisterFoodTruckReq registerFoodTruckReq) throws IllegalAccessException {
		User user = userService.getUserByEmail(jwtTokenUtil.getEmailFromBearerToken(bearerToken));
		foodTruckService.registerFoodTruck(registerFoodTruckReq, user);
		return ResponseEntity.ok().body(REGISTER_FOODTRUCK_SUCCESS);
	}

	// 푸드 트럭 수정
	@PatchMapping()
	@ApiOperation(value = "푸드트럭 수정", notes = "<strong>푸드트럭 정보를 수정한다.</strong>")
	public ResponseEntity<?> updateFoodTruck(@RequestHeader("Authorization") @ApiParam(value="Access Token", required = true) String bearerToken, @RequestBody @ApiParam(value="푸드트럭 정보", required = true) RegisterFoodTruckReq registerFoodTruckReq) throws IllegalAccessException {
		User user = userService.getUserByEmail(jwtTokenUtil.getEmailFromBearerToken(bearerToken));
		foodTruckService.updateFoodTruck(registerFoodTruckReq, user);
		return ResponseEntity.ok().body(UPDATE_FOODTRUCK_SUCCESS);
	}

	// 리뷰 등록
	@PostMapping("/review")
	@ApiOperation(value = "리뷰 등록", notes = "<strong>주문내역에 리뷰를 등록한다.</strong>")
	public ResponseEntity<?> registerFoodTruckReview(@RequestHeader("Authorization") @ApiParam(value="Access Token", required = true) String bearerToken, @RequestBody @ApiParam(value="리뷰 정보", required = true) RegisterFoodTruckReviewReq registerFoodTruckReviewReq){
		User user = userService.getUserByEmail(jwtTokenUtil.getEmailFromBearerToken(bearerToken));
		foodTruckService.registerFoodTruckReview(registerFoodTruckReviewReq, user);
		return ResponseEntity.ok().body(REGISTER_REVIEW_SUCCESS);
	}

	// 리뷰 조회
	@GetMapping("/review/{foodtruck_id}")
	@ApiOperation(value = "리뷰 조회", notes = "<strong>푸드트럭 ID에 해당하는 리뷰를 조회한다.</strong>")
	public ResponseEntity<?> getFoodTruckReview(@PathVariable("foodtruck_id") @ApiParam(value="푸드트럭 ID", required = true) Integer foodTruckId){
		List<GetFoodTruckReviewRes> getFoodTruckReviewResList = foodTruckService.getFoodTruckReview(foodTruckId);
		return ResponseEntity.ok().body(getFoodTruckReviewResList);
	}

	// 지도와 가까운 푸드트럭 조회
	@PostMapping("/near")
	@ApiOperation(value = "사용자 위치로 푸드트럭 조회", notes = "<strong>현재 위치에서 가까운 푸드트럭를 조회한다.</strong>")
	public ResponseEntity<Map<String, Object>> getNearFoodTruck(@RequestBody @ApiParam(value="사용자의 위치 정보와 카테고리", required = true) GetNearFoodTruckReq getNearFoodTruckReq){
		List<GetNearFoodTruckRes> foodTruckResList = foodTruckService.getNearFoodTruck(getNearFoodTruckReq);

		Map<String, Object> result = new HashMap<>();
		result.put("data", foodTruckResList);
		result.put("msg", GET_FOODTRUCK_SUCCESS);
		return ResponseEntity.ok().body(result);
	}

	// 푸드트럭 검색
	@GetMapping("/search/{keyword}")
	@ApiOperation(value = "푸드트럭 검색", notes = "<strong>키워드에 해당하는 푸드트럭을 검색해 조회한다.</strong>")
	public void search(){

	}

}
