package com.ssafy.foodtruck.controller;

import com.ssafy.foodtruck.dto.request.FindSurveyReq;
import com.ssafy.foodtruck.dto.request.SurveyReq;
import com.ssafy.foodtruck.dto.response.SurveyRes;
import com.ssafy.foodtruck.model.service.SurveyService;
import com.ssafy.foodtruck.util.JwtTokenUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ssafy.foodtruck.db.entity.Message.AUTHORIZATION;

@RequiredArgsConstructor
@RestController
@RequestMapping("/survey")
public class SurveyController {

	private final SurveyService surveyService;

	@PostMapping
	@ApiOperation(value = "설문조사 등록", notes = "<strong>사용자의 위치 기반으로 원하는 푸드트럭에 대한 설문조사를 등록한다.</strong>")
	public ResponseEntity<?> submitSurvey(@RequestHeader(AUTHORIZATION) String bearerToken, @RequestBody SurveyReq surveyReq) {
		int customerId = JwtTokenUtil.getUserIdFromBearerToken(bearerToken);
		surveyService.submitSurvey(customerId, surveyReq);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/find")
	@ApiOperation(value = "설문 조회", notes = "<strong>사업자들이 확인할 수 있는 설문조사 결과 리스트를 보여준다.</strong>")
	public ResponseEntity<List<SurveyRes>> getSurvey(@RequestHeader(AUTHORIZATION) String bearerToken, @RequestBody FindSurveyReq findSurveyReq) {
		int ceoId = JwtTokenUtil.getUserIdFromBearerToken(bearerToken);
		return new ResponseEntity<>(surveyService.getSurvey(ceoId, findSurveyReq), HttpStatus.OK);
	}
}
