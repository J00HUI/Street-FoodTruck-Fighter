package com.ssafy.foodtruck.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.foodtruck.dto.SmsAuthReq;
import com.ssafy.foodtruck.dto.response.SmsResponse;
import com.ssafy.foodtruck.model.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/phone")
public class SmsController {

	private final SmsService smsService;

	@PostMapping
	public ResponseEntity<SmsResponse> sendSms(@RequestParam String phoneNumber) throws NoSuchAlgorithmException, URISyntaxException, UnsupportedEncodingException, InvalidKeyException, JsonProcessingException {
		return new ResponseEntity<>(smsService.sendSms(phoneNumber), HttpStatus.OK);
	}

	@PostMapping("/sms")
	public ResponseEntity<String> authSms(@RequestBody SmsAuthReq smsAuthReq) {
		return new ResponseEntity<>(smsService.authSms(smsAuthReq), HttpStatus.OK);
	}
}
