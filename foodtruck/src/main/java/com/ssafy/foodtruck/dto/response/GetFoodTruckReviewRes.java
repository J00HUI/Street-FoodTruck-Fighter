package com.ssafy.foodtruck.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.foodtruck.db.entity.Review;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@Value	//lombok annotation to create constructor, equals and hash-code
@Builder
public class GetFoodTruckReviewRes{

	private Integer id;	// 리뷰 ID
	private Integer userId; // 작성자 ID
	private Integer ordersId; // 주문내역 ID
	private Integer grade; // 평점
	private String content; // 리뷰
	private String src; // 사진
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime regDate;	// 등록날짜
}
