package com.ssafy.foodtruck.model.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.foodtruck.dto.MessagesDto;
import com.ssafy.foodtruck.dto.SmsAuthReq;
import com.ssafy.foodtruck.dto.request.SmsRequest;
import com.ssafy.foodtruck.dto.response.SmsResponse;
import com.ssafy.foodtruck.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class SmsService {

	private final RedisUtil redisUtil;

	//	@Value("ncp:sms:kr:282120488007:foodtruck")
	private String serviceId = "ncp:sms:kr:282120488007:foodtruck";

	//	@Value("DqCfQas6ygsUPfyU4ljL")
	private String accessKey = "DqCfQas6ygsUPfyU4ljL";

	//	@Value("e5424aca94ee4f758bf62765dbb13c8a")
	private String secretKey = "GTVFjIAzyokUXcrff7pwGymtuk0MRefzaOMP8Jhf";

	public SmsResponse sendSms(String phoneNumber) throws JsonProcessingException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, URISyntaxException {
		Long time = System.currentTimeMillis();
		List<MessagesDto> messages = new ArrayList<>();
		Random random = new Random();
		String numStr = "";
		for (int i = 0; i < 6; i++) {
			String randomNumber = Integer.toString(random.nextInt(10));
			numStr += randomNumber;
		}
		messages.add(new MessagesDto(phoneNumber, "[스푸파] 인증번호 [" + numStr + "]를 입력해주세요."));


		SmsRequest smsRequest = new SmsRequest("SMS", "COMM", "82", "01085104523", "내용", messages);
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonBody = objectMapper.writeValueAsString(smsRequest);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("x-ncp-apigw-timestamp", time.toString());
		headers.set("x-ncp-iam-access-key", this.accessKey);
		String sig = makeSignature(time);
		headers.set("x-ncp-apigw-signature-v2", sig);

		HttpEntity<String> body = new HttpEntity<>(jsonBody, headers);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		SmsResponse smsResponse = restTemplate.postForObject(new URI("https://sens.apigw.ntruss.com/sms/v2/services/" + this.serviceId + "/messages"), body, SmsResponse.class);

		redisUtil.setDataExpired(phoneNumber, numStr, 60 * 5L);

		return smsResponse;
	}

	private String makeSignature(Long time) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {

		String space = " ";
		String newLine = "\n";
		String method = "POST";
		String url = "/sms/v2/services/" + this.serviceId + "/messages";
		String timestamp = time.toString();
		String accessKey = this.accessKey;
		String secretKey = this.secretKey;

		String message = new StringBuilder()
			.append(method)
			.append(space)
			.append(url)
			.append(newLine)
			.append(timestamp)
			.append(newLine)
			.append(accessKey)
			.toString();

		SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(signingKey);

		byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
		String encodeBase64String = Base64.encodeBase64String(rawHmac);

		return encodeBase64String;
	}

	public String authSms(SmsAuthReq smsAuthReq) {
		return redisUtil.validateData(smsAuthReq.getPhoneNumber(), smsAuthReq.getAuthToken());
	}
}
