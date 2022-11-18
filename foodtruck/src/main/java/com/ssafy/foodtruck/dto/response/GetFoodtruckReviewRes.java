package com.ssafy.foodtruck.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.foodtruck.db.entity.ReviewImg;
import lombok.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
//@Value	//lombok annotation to create constructor, equals and hash-code
public class GetFoodtruckReviewRes {

	private Integer id;	// 리뷰 ID
	private Integer userId; // 작성자 ID
	private Integer ordersId; // 주문내역 ID
	private Integer grade; // 평점
	private String content; // 리뷰
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime regDate;	// 등록날짜
	private byte[] src; //이미지

	public static GetFoodtruckReviewRes of(Integer id, Integer userId, Integer ordersId, Integer grade, String content, LocalDateTime regDate, ReviewImg reviewImg){
		GetFoodtruckReviewRes res = new GetFoodtruckReviewRes();
		res.setId(id);
		res.setUserId(userId);
		res.setOrdersId(ordersId);
		res.setGrade(grade);
		res.setContent(content);
		res.setRegDate(regDate);

		if(reviewImg!=null) {
			try {
				Path path = Paths.get(reviewImg.getSavedPath());
				byte[] isr = Files.readAllBytes(path);

				HttpHeaders respHeaders = new HttpHeaders();
				respHeaders.setContentLength(isr.length);
				respHeaders.setContentType(new MediaType("text", "json"));
				respHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
				respHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + reviewImg.getSavedNm());

				res.setSrc(isr);

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return res;
	}

}
