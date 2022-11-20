package com.ssafy.foodtruck.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PayApprovalDto {

	private String cid;
	private String tid;
	private String partner_order_id;
	private String partner_user_id;
}
