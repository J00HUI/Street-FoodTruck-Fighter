package com.ssafy.foodtruck.dto.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ssafy.foodtruck.common.BaseResponseBody;
import com.ssafy.foodtruck.db.entity.Category;
import com.ssafy.foodtruck.db.entity.FoodTruck;
import com.ssafy.foodtruck.db.entity.FoodtruckImg;
import com.ssafy.foodtruck.db.entity.Schedule;
import com.ssafy.foodtruck.dto.MenuDto;
import lombok.*;
import org.springframework.core.io.UrlResource;

import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetFoodtruckRes extends BaseResponseBody {

	private List<MenuDto> menuList = new ArrayList<>(); // 메뉴리스트

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

	private Boolean is_valid; //사용여부

	private Double latitude; // 위도

	private Double longitude; // 경도

	private String address; //주소

	private Double grade; //평점

	private Integer numberOfPeople; //대기인원

	private Integer time; //예상시간

	private UrlResource src; //이미지

	public static GetFoodtruckRes of(String message, List<MenuDto> menuList, FoodTruck foodTruck, Schedule schedule, Double grade, Integer numberOfPeople, Integer time, FoodtruckImg foodtruckImg) {
		GetFoodtruckRes res = new GetFoodtruckRes();
		res.setMessage(message);
		res.setMenuList(menuList);

		res.setName(foodTruck.getName());
		res.setCategory(foodTruck.getCategory());
		res.setPhone(foodTruck.getPhone());
		res.setDescription(foodTruck.getDescription());

		//setSrc
		try{
			res.setSrc(new UrlResource("file:" + foodtruckImg.getSavedPath()));
		} catch (MalformedURLException ex){
			ex.printStackTrace();
		}

		if(schedule != null) {
			res.setWorkingDate(schedule.getWorkingDate());
			res.setStartTime(schedule.getStartTime());
			res.setEndTime(schedule.getEndTime());
			res.setIs_valid(schedule.getIsValid());
			res.setLatitude(schedule.getLatitude());
			res.setLongitude(schedule.getLongitude());
			res.setAddress(schedule.getAddress());
		} else {
			res.setIs_valid(false);
		}

		res.setGrade(grade);
		res.setNumberOfPeople(numberOfPeople);
		res.setTime(time);
		return res;
	}
}
