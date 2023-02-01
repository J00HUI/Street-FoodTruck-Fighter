package com.ssafy.foodtruck.dto.response;

import lombok.Data;

@Data
public class PayReadyRes {

	String tid;
	String next_redirect_app_url;
	String next_redirect_mobile_url;
	String next_redirect_pc_url;
	String android_app_scheme;
	String ios_app_scheme;
	String created_at;
	boolean tms_result;

	String partner_order_id;
	String partner_user_id;

}
