package com.ssafy.foodtruck.dto.response;

import com.ssafy.foodtruck.db.entity.MenuImg;
import lombok.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetOrdersMenuRes {

	private String menuName;
	private Integer count;
	private byte[] src; 	// 메뉴 이미지

	public static GetOrdersMenuRes of(String menuName, Integer count, MenuImg menuImg){
		GetOrdersMenuRes res = new GetOrdersMenuRes();
		res.setMenuName(menuName);
		res.setCount(count);
		if(menuImg!=null) {
			try {
				Path path = Paths.get(menuImg.getSavedPath());
				byte[] isr = Files.readAllBytes(path);

				HttpHeaders respHeaders = new HttpHeaders();
				respHeaders.setContentLength(isr.length);
				respHeaders.setContentType(new MediaType("text", "json"));
				respHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
				respHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + menuImg.getSavedNm());

				res.setSrc(isr);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return res;
	}
}
