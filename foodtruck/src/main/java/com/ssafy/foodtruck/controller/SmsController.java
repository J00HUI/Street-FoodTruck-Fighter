package com.ssafy.foodtruck.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.foodtruck.dto.response.SmsResponse;
import com.ssafy.foodtruck.model.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/phone")
public class SmsController {

	private final SmsService smsService;

	@PostMapping("/sms")
	public ResponseEntity<SmsResponse> test(@RequestParam String phoneNumber) throws NoSuchAlgorithmException, URISyntaxException, UnsupportedEncodingException, InvalidKeyException, JsonProcessingException {
		SmsResponse data = smsService.sendSms(phoneNumber);
		return ResponseEntity.ok().body(data);
	}
}
