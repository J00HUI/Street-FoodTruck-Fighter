package com.ssafy.foodtruck.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class GetNearFoodtruckRes {

	private Integer foodtruckId;		// 푸드트럭 Id
	private List<MenuDto> menuList = new ArrayList<>(); // 메뉴리스트
	private String name; //상호명
	private Category category; //카테고리
	private String phone; //전화번호
	private String description; //설명
//	private UrlResource src; //이미지

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private LocalDate workingDate;	// 날짜

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Seoul")
	private LocalDateTime startTime; // 시작일시

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Seoul")
	private LocalDateTime endTime; // 종료일시
	private String title;	// 스케줄 타이틀
	private Integer groupId;	// 스케줄 그룹 ID
	private Double latitude; // 위도
	private Double longitude; // 경도
	private String address; //주소

	private Double grade; //평점

	public static GetNearFoodtruckRes of(List<MenuDto> menuList, FoodTruck foodTruck, Schedule schedule, Double grade, FoodtruckImg foodtruckImg) {
		GetNearFoodtruckRes res = new GetNearFoodtruckRes();
		res.setFoodtruckId(foodTruck.getId());
		res.setMenuList(menuList);
		res.setName(foodTruck.getName());
		res.setCategory(foodTruck.getCategory());
		res.setPhone(foodTruck.getPhone());
		res.setDescription(foodTruck.getDescription());

		if(schedule != null){
			res.setWorkingDate(schedule.getWorkingDate());
			res.setStartTime(schedule.getStartTime());
			res.setEndTime(schedule.getEndTime());
			res.setLatitude(schedule.getLatitude());
			res.setLongitude(schedule.getLongitude());
			res.setAddress(schedule.getAddress());
			res.setTitle(schedule.getTitle());
			res.setGroupId(schedule.getGroupId());
		}

		res.setGrade(grade);

		//setSrc
//		try{
//			res.setSrc(new UrlResource("file:" + foodtruckImg.getSavedPath()));
//		} catch (MalformedURLException ex){
//			ex.printStackTrace();
//		}

		return res;
	}
}
