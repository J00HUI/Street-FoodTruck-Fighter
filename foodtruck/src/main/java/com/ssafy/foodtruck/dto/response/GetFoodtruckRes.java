package com.ssafy.foodtruck.dto.response;

import com.fasterxml.jackson.annotation.*;
import com.ssafy.foodtruck.common.BaseResponseBody;
import com.ssafy.foodtruck.db.entity.Category;
import com.ssafy.foodtruck.db.entity.FoodTruck;
import com.ssafy.foodtruck.db.entity.FoodtruckImg;
import com.ssafy.foodtruck.db.entity.Schedule;
import lombok.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class GetFoodtruckRes extends BaseResponseBody {

	private List<MenuRes> menuList = new ArrayList<>(); // 메뉴리스트

	private String name; //상호명

	private Category category; //카테고리

	private String phone; //전화번호

	private String description; //설명

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private LocalDate workingDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Seoul")
	private LocalDateTime startTime;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Seoul")
	private LocalDateTime endTime;

	private String title;

	private Integer groupId;

	private Boolean is_valid; //사용여부

	private Double latitude; // 위도

	private Double longitude; // 경도

	private String address; //주소

	private Double grade; //평점

	private Integer numberOfPeople; //대기인원

	private Integer time; //예상시간

//	@JsonIgnore
	private byte[] src; //이미지

	public static GetFoodtruckRes of(String message, List<MenuRes> menuList, FoodTruck foodTruck, Schedule schedule, Double grade, Integer numberOfPeople, Integer time, FoodtruckImg foodtruckImg) {
		GetFoodtruckRes res = new GetFoodtruckRes();
		res.setMessage(message);
		res.setMenuList(menuList);

		res.setName(foodTruck.getName());
		res.setCategory(foodTruck.getCategory());
		res.setPhone(foodTruck.getPhone());
		res.setDescription(foodTruck.getDescription());

		//setSrc
//		try{
//			res.setSrc(new UrlResource("file:" + foodtruckImg.getSavedPath()));
//		} catch (MalformedURLException ex){
//			ex.printStackTrace();
//		}

//		try {
//			Path path = Paths.get(foodtruckImg.getSavedPath());
//			Resource resoure = new InputStreamResource(Files.newInputStream(path));
//			File file = new File(foodtruckImg.getSavedPath());
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}

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

//		if(schedule != null) {
//			res.setWorkingDate(schedule.getWorkingDate());
//			res.setStartTime(schedule.getStartTime());
//			res.setEndTime(schedule.getEndTime());
//			res.setIs_valid(schedule.getIsValid());
//			res.setLatitude(schedule.getLatitude());
//			res.setLongitude(schedule.getLongitude());
//			res.setAddress(schedule.getAddress());
//			res.setTitle(schedule.getTitle());
//			res.setGroupId(schedule.getGroupId());
//		} else {
//			res.setIs_valid(false);
//		}

		res.setGrade(grade);
		res.setNumberOfPeople(numberOfPeople);
		res.setTime(time);
		return res;
	}
}
