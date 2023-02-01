package com.ssafy.foodtruck.model.service;

import com.ssafy.foodtruck.db.entity.*;
import com.ssafy.foodtruck.db.repository.*;
import com.ssafy.foodtruck.dto.response.MenuRes;
import com.ssafy.foodtruck.dto.request.GetNearFoodtruckReq;
import com.ssafy.foodtruck.dto.request.RegisterFoodtruckReq;
import com.ssafy.foodtruck.dto.response.GetFoodtruckRes;
import com.ssafy.foodtruck.dto.response.GetNearFoodtruckRes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.ssafy.foodtruck.constant.FoodtruckConstant.*;

@Service("foodTruckService")
@RequiredArgsConstructor
public class FoodTruckService {

	@Value("${file.dir}")
	private String fileDir;
	private final FoodtruckRepository foodTruckRepository;
	private final ScheduleRepository scheduleRepository;
	private final MenuRepository menuRepository;
	private final ReviewRepository reviewRepository;

	// 푸드트럭 정보 조회
	public GetFoodtruckRes getFoodTruck(Integer foodTruckId){

		FoodTruck foodTruck = foodTruckRepository.findById(foodTruckId)
			.orElseThrow(() -> new IllegalArgumentException(NOT_FOUNT_FOODTRUCK_ERROR_MESSAGE));


		List<Menu> findMenuList = menuRepository.findAllByFoodTruck(foodTruck);
		List<MenuRes> menuList = new ArrayList<>();
		for(Menu menu : findMenuList){
			menuList.add(MenuRes.of(menu));
		}

		Double grade = 0.0;
		List<Review> findReviewList = reviewRepository.findAllByFoodTruckId(foodTruckId);
		for(Review r : findReviewList){
			grade += r.getGrade();
		}
		grade /= findReviewList.size();
		Integer numberOfPeople = 0;
		Integer time = 0;

		FoodtruckImg foodtruckImg = foodTruck.getFoodtruckImg();

		return GetFoodtruckRes.of(GET_FOODTRUCK_SUCCESS, menuList, foodTruck, null, grade, numberOfPeople, time, foodtruckImg);
	}

	// 내 푸드트럭 등록
	public void registerFoodTruck(RegisterFoodtruckReq registerFoodTruckReq, User user) throws Exception {
		// 중복된 푸드트럭이 등록되었는지 검사
		FoodTruck foodTruckUser = foodTruckRepository.findByUser(user).orElse(null);

		if(foodTruckUser != null){
			throw new IllegalAccessException(DUPLICATED_FOODTRUCK_ERROR_MESSAGE);
		}

		// 푸드트럭 등록
		final FoodTruck foodTruck = FoodTruck.builder()
			.user(user)
			.category(registerFoodTruckReq.getCategory())
			.description(registerFoodTruckReq.getDescription())
			.name(registerFoodTruckReq.getName())
			.phone(registerFoodTruckReq.getPhone())
			.build();

		foodTruckRepository.save(foodTruck);
	}

	// 푸드트럭 수정
	@Transactional
	public void updateFoodTruck(RegisterFoodtruckReq registerFoodTruckReq, User user) {
		// 푸드트럭 찾기
		FoodTruck foodTruck = foodTruckRepository.findByUser(user)
			.orElseThrow(() -> new IllegalArgumentException(NOT_FOUNT_FOODTRUCK_ERROR_MESSAGE));

		// 푸드트럭 수정
		foodTruck.update(registerFoodTruckReq);
	}

	// 현재 위치와 가까운 푸드트럭 조회
	public List<GetNearFoodtruckRes> getNearFoodTruck(GetNearFoodtruckReq getNearFoodTruckReq){
		List<Schedule> scheduleList = scheduleRepository.findScheduleNearBy(getNearFoodTruckReq.getLat(),getNearFoodTruckReq.getLng());
		if(scheduleList.isEmpty())
			throw new IllegalArgumentException(NOT_FOUND_SCHEDULE_ERROR_MESSAGE);

		List<GetNearFoodtruckRes> foodTruckList = new ArrayList<>();
		for(Schedule schedule : scheduleList) {
			// 카테고리에 해당하는 푸드트럭
			FoodTruck foodTruck = schedule.getFoodTruck();
			if(foodTruck == null)
				throw new IllegalArgumentException(NOT_FOUNT_FOODTRUCK_ERROR_MESSAGE);

			if(!getNearFoodTruckReq.getCategory().equals(Category.전체) && foodTruck.getCategory() != getNearFoodTruckReq.getCategory()) continue;

			List<Menu> menuList = menuRepository.findAllByFoodTruck(foodTruck);
			List<MenuRes> menuResList = new ArrayList<>();
			for(Menu menu : menuList){
				menuResList.add(MenuRes.of(menu));
			}

			Double grade = 0.0;
			List<Review> findReviewList = reviewRepository.findAllByFoodTruckId(foodTruck.getId());
//			if(findReviewList.isEmpty()){
//				throw new IllegalArgumentException("리뷰가 없습니다.");
//			}

			for(Review r : findReviewList){
				grade += r.getGrade();
			}
			grade /= findReviewList.size();

			FoodtruckImg foodtruckImg = foodTruck.getFoodtruckImg();

			foodTruckList.add(GetNearFoodtruckRes.of(menuResList, foodTruck, schedule, grade, foodtruckImg));
		}

		return foodTruckList;
	}

	// 푸드트럭 검색
	public List<GetNearFoodtruckRes> searchFoodTruck(String keyword){
		// 키워드에 해당하는 푸드트럭 ID List 구하기
		List<Integer> foodTruckIdList = foodTruckRepository.findAllByKeyword(keyword);

		List<GetNearFoodtruckRes> foodTruckList = new ArrayList<>();

		// 푸드트럭 ID 에 해당하는 푸드트럭 정보들 리턴
		for(Integer foodTruckId : foodTruckIdList){
			FoodTruck foodTruck = foodTruckRepository.findById(foodTruckId)
				.orElseThrow(() -> new IllegalArgumentException(NOT_FOUNT_FOODTRUCK_ERROR_MESSAGE));

			List<Menu> menuList = menuRepository.findAllByFoodTruck(foodTruck);
			List<MenuRes> menuResList = new ArrayList<>();
			for(Menu menu : menuList){
				menuResList.add(MenuRes.of(menu));
			}

			Double grade = 0.0;
			List<Review> findReviewList = reviewRepository.findAllByFoodTruckId(foodTruckId);
			for(Review r : findReviewList){
				grade += r.getGrade();
			}
			grade /= findReviewList.size();

			Schedule schedule = scheduleRepository.findScheduleByFoodTruckAndDate(foodTruckId).orElse(null);
//			if(schedule == null) 오늘은 운영시간이 아닙니다. 테스트 케이스 작성

			FoodtruckImg foodtruckImg = foodTruck.getFoodtruckImg();

			foodTruckList.add(GetNearFoodtruckRes.of(menuResList, foodTruck, schedule, grade, foodtruckImg));
		}
		return foodTruckList;
	}

	public FoodTruck getFoodTruckByUser(User user){
		return foodTruckRepository.findByUser(user).orElse(null);
	}

	@Transactional
	public void saveFoodtruckImg(User user, MultipartFile files) throws IOException {
		Optional<FoodTruck> foodTruck = foodTruckRepository.findByUser(user);

		if(!foodTruck.isPresent()) {
			return;
		}

		if (files.isEmpty()) {
			return;
		}

		// 원래 파일 이름 추출
		String origName = files.getOriginalFilename();

		// 파일 이름으로 쓸 uuid 생성
		String uuid = UUID.randomUUID().toString();

		// 확장자 추출(ex : .png)
		String extension = origName.substring(origName.lastIndexOf("."));

		// uuid와 확장자 결합
		String savedName = uuid + extension;

		// 파일을 불러올 때 사용할 파일 경로
		String savedPath = fileDir + savedName;

		// 파일 엔티티 생성
		FoodtruckImg file = FoodtruckImg.builder()
			.orgNm(origName)
			.savedNm(savedName)
			.savedPath(savedPath)
//			.foodTruck(foodTruck.get())
			.build();

		// 실제로 로컬에 uuid를 파일명으로 저장
		files.transferTo(new File(savedPath));

		foodTruck.get().setFoodtruckImg(file);
	}

	public FoodtruckImg getFoodtruckImg(int foodtruckId) {

		Optional<FoodTruck> foodTruck = foodTruckRepository.findById(foodtruckId);
		if(!foodTruck.isPresent()){
			return null;
		}

		return foodTruck.get().getFoodtruckImg();
	}
}
