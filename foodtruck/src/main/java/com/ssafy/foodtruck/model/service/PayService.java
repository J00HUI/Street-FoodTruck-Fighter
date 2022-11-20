package com.ssafy.foodtruck.model.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.foodtruck.dto.PayApprovalDto;
import com.ssafy.foodtruck.dto.request.PayApprovalReq;
import com.ssafy.foodtruck.dto.response.PayApprovalRes;
import com.ssafy.foodtruck.dto.response.PayReadyRes;
import com.ssafy.foodtruck.dto.response.RegisterOrdersRes;
import lombok.RequiredArgsConstructor;
import org.hibernate.result.Output;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Service("payService")
@RequiredArgsConstructor
public class PayService {

	ObjectMapper mapper = new ObjectMapper();

	private static final String HOST = "https://kapi.kakao.com";
	private static final String READY_ADDR = HOST + "/v1/payment/ready";
	private static final String APPROVE_ADDR = HOST + "/v1/payment/approve";
	private static final String ADMIN_KEY = "16d8a229832ac9d7e96d2dbf469efe17";
//	private static final String DOMAIN = "https://localhost:8080/api/v1/pay";
	private static final String DOMAIN = "https://k7b206.p.ssafy.io";

	public PayReadyRes payReady(RegisterOrdersRes registerOrdersRes){

		try{
			HttpURLConnection conn = (HttpURLConnection) new URL(READY_ADDR).openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "KakaoAK " + ADMIN_KEY);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			conn.setDoOutput(true);

			String param = makePayQuery(registerOrdersRes);

			OutputStream out = conn.getOutputStream();
			DataOutputStream dout = new DataOutputStream(out);
			dout.writeBytes(param);
			dout.close();

			int res = conn.getResponseCode();
			System.out.println("code: " + res);
			InputStream in;

			if(res == 200){
				in = conn.getInputStream();
			} else {
				in = conn.getErrorStream();
			}

			InputStreamReader inr = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(inr);
			PayReadyRes payReadyRes = mapper.readValue(br.readLine(), PayReadyRes.class);
			System.out.println(payReadyRes.toString());
			return payReadyRes;

		} catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}

	private String makePayQuery(RegisterOrdersRes registerOrdersRes) throws UnsupportedEncodingException {
		return "cid=TC0ONETIME&" +
			"partner_order_id="+ String.valueOf(registerOrdersRes.getOrders().getId()) +"&" +
			"partner_user_id=" + String.valueOf(registerOrdersRes.getOrders().getUser().getId()) +"&" +
			"item_name=" + URLEncoder.encode(registerOrdersRes.getPayMenuName(), "UTF-8") +"&" +
			"quantity=" + registerOrdersRes.getTotalQuantity() + "&" +
			"total_amount=" + registerOrdersRes.getTotalAmount() + "&" +
			"vat_amount=200&" + //세금
			"tax_free_amount=0&" + //비과세 금액
			"approval_url=" + DOMAIN + "/success&" +
			"cancel_url=" + DOMAIN+  "/cancel&" +
			"fail_url=" + DOMAIN + "/fail";
	}

	public ResponseEntity<PayApprovalRes> paySuccess(PayApprovalReq payApprovalReq, String pg_token){
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		headers.add("Authorization", "KakaoAK " + ADMIN_KEY);
		headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.add("Content-Type",MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("cid", "TC0ONETIME");
		params.add("tid", payApprovalReq.getTid());
		params.add("partner_order_id",payApprovalReq.getPartner_order_id());
		params.add("partner_user_id",payApprovalReq.getPartner_user_id());
		params.add("pg_token",pg_token);

		HttpEntity<MultiValueMap<String,String>> body
			= new HttpEntity<>(params, headers);
		return restTemplate.exchange(APPROVE_ADDR , HttpMethod.POST, body, PayApprovalRes.class);
	}
}
