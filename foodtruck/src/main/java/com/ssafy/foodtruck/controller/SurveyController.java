package com.ssafy.foodtruck.controller;

import com.ssafy.foodtruck.db.entity.User;
import com.ssafy.foodtruck.dto.request.SurveyReq;
import com.ssafy.foodtruck.dto.response.SurveyRes;
import com.ssafy.foodtruck.model.service.SurveyService;
import com.ssafy.foodtruck.model.service.UserService;
import com.ssafy.foodtruck.util.JwtTokenUtil;
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
	public ResponseEntity<?> submitSurvey(@RequestHeader(AUTHORIZATION) String bearerToken, @RequestBody SurveyReq surveyReq) {
		int customerId = JwtTokenUtil.getUserIdFromBearerToken(bearerToken);
		surveyService.submitSurvey(customerId, surveyReq);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<SurveyRes>> getSurvey(@RequestHeader(AUTHORIZATION) String bearerToken, @RequestBody SurveyReq surveyReq) {
		int ceoId = JwtTokenUtil.getUserIdFromBearerToken(bearerToken);
		return new ResponseEntity<>(surveyService.getSurvey(ceoId, surveyReq), HttpStatus.OK);
	}
}
