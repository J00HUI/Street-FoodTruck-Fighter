package com.ssafy.foodtruck.dto.response;

import com.ssafy.foodtruck.dto.AmountDto;
import com.ssafy.foodtruck.dto.CardDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PayApprovalRes {

	private String aid, tid, cid, sid;
	private String partner_order_id, partner_user_id, payment_method_type;
	private AmountDto amountDto;
	private CardDto cardDto;
	private String item_name, item_code, payload;
	private Integer quantity, tax_free_amount, vat_amount;
	private LocalDateTime created_at, approved_at;
}
