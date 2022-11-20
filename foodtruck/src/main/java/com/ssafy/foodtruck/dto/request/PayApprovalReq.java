package com.ssafy.foodtruck.dto.request;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PayApprovalReq {

	private String tid;
	private String partner_order_id;
	private String partner_user_id;
}
