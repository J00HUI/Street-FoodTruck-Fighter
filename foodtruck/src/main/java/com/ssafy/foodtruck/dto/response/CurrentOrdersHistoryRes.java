package com.ssafy.foodtruck.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.foodtruck.db.entity.FoodtruckImg;
import lombok.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CurrentOrdersHistoryRes {
	private Integer ordersId;
    private String foodtruckName;
	private byte[] src;	// 푸드트럭 이미지

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime acceptTime;
	List<GetOrdersMenuRes> menuList = new ArrayList<>();
	//menuName, count

	public static CurrentOrdersHistoryRes of(Integer ordersId, String foodtruckName, FoodtruckImg foodtruckImg, LocalDateTime acceptTime, List<GetOrdersMenuRes> menuList){
		CurrentOrdersHistoryRes res = new CurrentOrdersHistoryRes();
		res.setOrdersId(ordersId);
		res.setFoodtruckName(foodtruckName);

		if(foodtruckImg!=null) {
			try {
				Path path = Paths.get(foodtruckImg.getSavedPath());
				byte[] isr = Files.readAllBytes(path);

				HttpHeaders respHeaders = new HttpHeaders();
				respHeaders.setContentLength(isr.length);
				respHeaders.setContentType(new MediaType("text", "json"));
				respHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
				respHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + foodtruckImg.getSavedNm());

				res.setSrc(isr);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		res.setAcceptTime(acceptTime);
		res.setMenuList(menuList);
		return res;
	}
}
