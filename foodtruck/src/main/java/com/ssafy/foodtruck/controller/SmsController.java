package com.ssafy.foodtruck.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.foodtruck.dto.response.SmsResponse;
import com.ssafy.foodtruck.model.service.SmsService;
import com.ssafy.foodtruck.util.RedisUtil;
import lombok.RequiredArgsConstructor;
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
	private final RedisUtil redisUtil;

	@PostMapping("/sms")
	public ResponseEntity<SmsResponse> test(@RequestParam String phoneNumber) throws NoSuchAlgorithmException, URISyntaxException, UnsupportedEncodingException, InvalidKeyException, JsonProcessingException {
		SmsResponse data = smsService.sendSms(phoneNumber);
		return ResponseEntity.ok().body(data);
	}

	@GetMapping("")
	public void test1(){
		redisUtil.set("1","lee bum yun",100000000);
	}


}
