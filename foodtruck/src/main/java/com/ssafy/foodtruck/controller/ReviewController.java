package com.ssafy.foodtruck.controller;

import com.ssafy.foodtruck.db.entity.FoodtruckImg;
import com.ssafy.foodtruck.db.entity.ReviewImg;
import com.ssafy.foodtruck.db.entity.User;
import com.ssafy.foodtruck.dto.request.RegisterFoodtruckReviewReq;
import com.ssafy.foodtruck.dto.response.GetFoodtruckReviewRes;
import com.ssafy.foodtruck.model.service.ReviewService;
import com.ssafy.foodtruck.model.service.UserService;
import com.ssafy.foodtruck.util.JwtTokenUtil;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.ssafy.foodtruck.constant.FoodtruckConstant.REGISTER_REVIEW_SUCCESS;

@Api(value = "리뷰 API", tags = {"Review"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

	private final UserService userService;
	private final ReviewService reviewService;
	private final JwtTokenUtil jwtTokenUtil;

	// 리뷰 등록
	@PostMapping
	@ApiOperation(value = "리뷰 등록", notes = "<strong>주문내역에 리뷰를 등록한다.</strong>")
	public ResponseEntity<?> registerFoodTruckReview(@RequestHeader("Authorization") @ApiParam(value="Access Token", required = true) String bearerToken,
													 @RequestBody @ApiParam(value="리뷰 정보", required = true) RegisterFoodtruckReviewReq registerFoodTruckReviewReq){
		User user = userService.getUserByEmail(jwtTokenUtil.getEmailFromBearerToken(bearerToken));

		reviewService.registerFoodTruckReview(registerFoodTruckReviewReq, user);
		return new ResponseEntity<>(REGISTER_REVIEW_SUCCESS, HttpStatus.CREATED);
	}

	// 리뷰 조회
	@GetMapping("/{foodtruck_id}")
	@ApiOperation(value = "리뷰 조회", notes = "<strong>푸드트럭 ID에 해당하는 리뷰를 조회한다.</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공", response = GetFoodtruckReviewRes.class),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "사용자 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<?> getFoodTruckReview(@PathVariable("foodtruck_id") @ApiParam(value="푸드트럭 ID", required = true) Integer foodTruckId){
		List<GetFoodtruckReviewRes> getFoodTruckReviewResList = reviewService.getFoodTruckReview(foodTruckId);
		return new ResponseEntity<>(getFoodTruckReviewResList, HttpStatus.OK);
	}

	@PostMapping("/upload")
	public ResponseEntity<HttpStatus> saveReviewImg(@RequestHeader("Authorization") @ApiParam(value="Access Token", required = true) String bearerToken,
													@RequestHeader Integer ordersId, @RequestParam("file") MultipartFile file) throws IOException {
		int customerId = JwtTokenUtil.getUserIdFromBearerToken(bearerToken);
		//본인 리뷰인지 확인하는 과정 필요

		reviewService.saveReviewImg(ordersId, file);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/image/{review_id}")
	@ResponseBody
	public ResponseEntity<UrlResource> getReviewImg(@PathVariable Integer reviewId) throws IOException {
		ReviewImg file = reviewService.getReviewImg(reviewId);
		return new ResponseEntity<>(new UrlResource("file:" + file.getSavedPath()), HttpStatus.OK);
	}

}
