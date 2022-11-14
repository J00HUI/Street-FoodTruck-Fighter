package com.ssafy.foodtruck.controller;

import com.ssafy.foodtruck.db.entity.FileEntity;
import com.ssafy.foodtruck.db.entity.User;
import com.ssafy.foodtruck.dto.request.GetNearFoodtruckReq;
import com.ssafy.foodtruck.dto.request.RegisterFoodtruckReq;
import com.ssafy.foodtruck.dto.request.RegisterFoodtruckReviewReq;
import com.ssafy.foodtruck.dto.response.GetFoodtruckRes;
import com.ssafy.foodtruck.dto.response.GetFoodtruckReviewRes;
import com.ssafy.foodtruck.dto.response.GetNearFoodtruckRes;
import com.ssafy.foodtruck.model.service.FoodTruckService;
import com.ssafy.foodtruck.model.service.UserService;
import com.ssafy.foodtruck.util.JwtTokenUtil;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.ssafy.foodtruck.constant.FoodtruckConstant.*;

import java.io.IOException;
import java.util.*;

/**
 * 푸드트럭 관련 API 요청 처리를 위한 컨트롤러 정의
 */
@Api(value = "푸드트럭 API", tags = {"FoodTruck"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/foodtruck")
public class FoodtruckController {

	private final FoodTruckService foodTruckService;

	private final UserService userService;

	private final JwtTokenUtil jwtTokenUtil;

//	@GetMapping
//	@ApiOperation(value = "본인 푸드트럭 조회", notes = "<strong>본인 푸드트럭 정보를 조회한다.</strong>")
//	@ApiResponses({
//		@ApiResponse(code = 200, message = "성공", response = FoodtruckRes.class),
//		@ApiResponse(code = 401, message = "인증 실패"),
//		@ApiResponse(code = 404, message = "사용자 없음"),
//		@ApiResponse(code = 500, message = "서버 오류")
//	})
//	public ResponseEntity<FoodtruckRes> getFoodTruck(@RequestHeader("Authorization") String bearerToken) {
//		User user = userService.getUserByEmail(jwtTokenUtil.getEmailFromBearerToken(bearerToken));
//		FoodTruck foodTruck = foodTruckService.getFoodTruckByUser(user);
//		return new ResponseEntity<>(FoodtruckRes.of(foodTruck), HttpStatus.OK);
//	}

	// 푸드트럭 정보를 가져옴
	@GetMapping("/{foodtruck_id}")
	@ApiOperation(value = "푸드트럭 ID 로 푸드트럭 조회", notes = "<strong>푸드트럭 ID 를 통해 푸드트럭 정보를 조회한다.</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공", response = GetFoodtruckRes.class),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "사용자 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<?> getFoodTruck(@PathVariable("foodtruck_id") @ApiParam(value="푸드트럭 ID", required = true) Integer foodTruckId){
		try {
			GetFoodtruckRes getFoodTruckRes = foodTruckService.getFoodTruck(foodTruckId);
			return new ResponseEntity<>(getFoodTruckRes, HttpStatus.OK);
		} catch (IllegalArgumentException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// 푸드트럭 등록
	@PostMapping()
	@ApiOperation(value = "푸드트럭 등록", notes = "<strong>내 푸드트럭을 등록한다.</strong>")
	public ResponseEntity<?> registerFoodTruck(@RequestHeader("Authorization") @ApiParam(value="Access Token", required = true) String bearerToken, @RequestBody @ApiParam(value="푸드트럭 정보", required = true) RegisterFoodtruckReq registerFoodTruckReq) throws IllegalAccessException {
		User user = userService.getUserByEmail(jwtTokenUtil.getEmailFromBearerToken(bearerToken));

		try {
			foodTruckService.registerFoodTruck(registerFoodTruckReq, user);
			return new ResponseEntity<>(REGISTER_FOODTRUCK_SUCCESS, HttpStatus.CREATED);
		} catch (IllegalAccessException ex) {
			return new ResponseEntity<>(DUPLICATED_FOODTRUCK_ERROR_MESSAGE, HttpStatus.BAD_REQUEST);
		}
	}

	// 푸드 트럭 수정
	@PatchMapping()
	@ApiOperation(value = "푸드트럭 수정", notes = "<strong>푸드트럭 정보를 수정한다.</strong>")
	public ResponseEntity<?> updateFoodTruck(@RequestHeader("Authorization") @ApiParam(value="Access Token", required = true) String bearerToken, @RequestBody @ApiParam(value="푸드트럭 정보", required = true) RegisterFoodtruckReq registerFoodTruckReq) throws IllegalAccessException {
		User user = userService.getUserByEmail(jwtTokenUtil.getEmailFromBearerToken(bearerToken));
		foodTruckService.updateFoodTruck(registerFoodTruckReq, user);
		return new ResponseEntity<>(UPDATE_FOODTRUCK_SUCCESS, HttpStatus.OK);
	}

	// 리뷰 등록
	@PostMapping("/review")
	@ApiOperation(value = "리뷰 등록", notes = "<strong>주문내역에 리뷰를 등록한다.</strong>")
	public ResponseEntity<?> registerFoodTruckReview(@RequestHeader("Authorization") @ApiParam(value="Access Token", required = true) String bearerToken, @RequestBody @ApiParam(value="리뷰 정보", required = true) RegisterFoodtruckReviewReq registerFoodTruckReviewReq){
		User user = userService.getUserByEmail(jwtTokenUtil.getEmailFromBearerToken(bearerToken));
		foodTruckService.registerFoodTruckReview(registerFoodTruckReviewReq, user);
		return new ResponseEntity<>(REGISTER_REVIEW_SUCCESS, HttpStatus.CREATED);
	}

	// 리뷰 조회
	@GetMapping("/review/{foodtruck_id}")
	@ApiOperation(value = "리뷰 조회", notes = "<strong>푸드트럭 ID에 해당하는 리뷰를 조회한다.</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공", response = GetFoodtruckReviewRes.class),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "사용자 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<?> getFoodTruckReview(@PathVariable("foodtruck_id") @ApiParam(value="푸드트럭 ID", required = true) Integer foodTruckId){
		List<GetFoodtruckReviewRes> getFoodTruckReviewResList = foodTruckService.getFoodTruckReview(foodTruckId);
		return new ResponseEntity<>(getFoodTruckReviewResList, HttpStatus.OK);
//		return ResponseEntity.ok().body(getFoodTruckReviewResList);
	}

	// 지도와 가까운 푸드트럭 조회
	@PostMapping("/near")
	@ApiOperation(value = "사용자 위치로 푸드트럭 조회", notes = "<strong>현재 위치에서 가까운 푸드트럭를 조회한다.</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공", response = GetNearFoodtruckRes.class),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "사용자 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<List<GetNearFoodtruckRes>> getNearFoodTruck(@RequestBody @ApiParam(value="사용자의 위치 정보와 카테고리", required = true) GetNearFoodtruckReq getNearFoodTruckReq){
		List<GetNearFoodtruckRes> foodTruckResList = foodTruckService.getNearFoodTruck(getNearFoodTruckReq);
		return new ResponseEntity<>(foodTruckResList, HttpStatus.OK);
	}

	// 푸드트럭 검색
	@GetMapping("/search/{keyword}")
	@ApiOperation(value = "푸드트럭 검색", notes = "<strong>상호명, 설명, 메뉴, 카테고리에 해당 키워드를 포함된 푸드트럭정보를 가져온다.</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공", response = GetNearFoodtruckRes.class),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "사용자 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<List<GetNearFoodtruckRes>> search(@PathVariable("keyword") @ApiParam(value="검색 키워드", required = true) String keyword){
		List<GetNearFoodtruckRes> foodTruckResList = foodTruckService.searchFoodTruck(keyword);
		return new ResponseEntity<>(foodTruckResList, HttpStatus.OK);
	}

	@PostMapping("/upload")
	public ResponseEntity<HttpStatus> saveFile(@RequestHeader("Authorization") String bearerToken, @RequestParam("file") MultipartFile file) throws IOException {
		int ceoId = JwtTokenUtil.getUserIdFromBearerToken(bearerToken);
		foodTruckService.saveFile(ceoId, file);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/images")
	@ResponseBody
	public ResponseEntity<UrlResource> getFile(@RequestHeader("Authorization") String bearerToken) throws IOException{
		int ceoId = JwtTokenUtil.getUserIdFromBearerToken(bearerToken);
		FileEntity file = foodTruckService.getFile(ceoId);
		return new ResponseEntity<>(new UrlResource("file:" + file.getSavedPath()), HttpStatus.OK);
	}

}
