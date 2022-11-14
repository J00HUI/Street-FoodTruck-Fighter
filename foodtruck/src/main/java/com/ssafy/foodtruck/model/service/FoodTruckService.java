package com.ssafy.foodtruck.model.service;

import com.ssafy.foodtruck.db.entity.*;
import com.ssafy.foodtruck.db.repository.*;
import com.ssafy.foodtruck.dto.MenuDto;
import com.ssafy.foodtruck.dto.ScheduleDateDto;
import com.ssafy.foodtruck.dto.request.GetNearFoodTruckReq;
import com.ssafy.foodtruck.dto.request.RegisterFoodTruckReq;
import com.ssafy.foodtruck.dto.request.RegisterFoodTruckReviewReq;
import com.ssafy.foodtruck.dto.response.GetFoodTruckRes;
import com.ssafy.foodtruck.dto.response.GetFoodTruckReviewRes;
import com.ssafy.foodtruck.dto.response.GetNearFoodTruckRes;
import com.ssafy.foodtruck.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.ssafy.foodtruck.constant.FoodTruckConstant.*;

@Service("foodTruckService")
@RequiredArgsConstructor
public class FoodTruckService {

	@Value("${file.dir}")
	private String fileDir;
	private final FoodTruckRepository foodTruckRepository;
	private final ScheduleRepository scheduleRepository;
	private final MenuRepository menuRepository;
	private final OrdersRepository ordersRepository;
	private final ReviewRepository reviewRepository;
	private final FileRepository fileRepository;
	private final UserRepository userRepository;
	// 푸드트럭 정보 조회
	public GetFoodTruckRes getFoodTruck(Integer foodTruckId){

		FoodTruck foodTruck = foodTruckRepository.findById(foodTruckId)
			.orElseThrow(() -> new IllegalArgumentException(NOT_FOUNT_FOODTRUCK_ERROR_MESSAGE));

		Schedule schedule = scheduleRepository.findScheduleByFoodTruckAndDate(foodTruckId).orElse(null);
//		if(schedule == null) 오늘은 영업시간이 아닙니다.

		List<Menu> findMenuList = menuRepository.findAllByFoodTruck(foodTruck);
		List<MenuDto> menuList = new ArrayList<>();
		for(Menu menu : findMenuList){
			menuList.add(MenuDto.of(menu));
		}

		Double grade = 0.0;
		List<Review> findReviewList = reviewRepository.findAllByFoodTruckId(foodTruckId);
		for(Review r : findReviewList){
			grade += r.getGrade();
		}
		grade /= findReviewList.size();
		Integer numberOfPeople = 0;
		Integer time = 0;

//		return GetFoodTruckRes.of(menuList, foodTruck, grade, numberOfPeople, time);
		return GetFoodTruckRes.of(menuList, foodTruck, schedule, grade, numberOfPeople, time);
	}

	// 내 푸드트럭 등록
	public void registerFoodTruck(RegisterFoodTruckReq registerFoodTruckReq, User user) throws IllegalAccessException {
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

		FoodTruck savedFoodTruck = foodTruckRepository.save(foodTruck);

		// 메뉴 등록
		for(MenuDto menuDto : registerFoodTruckReq.getMenuList()){
			final Menu menu = Menu.builder()
				.name(menuDto.getName())
				.foodTruck(savedFoodTruck)
				.price(menuDto.getPrice())
				.description(menuDto.getDescription())
				.src(menuDto.getSrc()).build();

			menuRepository.save(menu);
		}

		// 스케쥴 등록
		for(ScheduleDateDto dateDto : registerFoodTruckReq.getDateDtoList()){
			final Schedule schedule = Schedule.builder()
				.foodTruck(savedFoodTruck)
				.latitude(registerFoodTruckReq.getLatitude())
				.longitude(registerFoodTruckReq.getLongtitue())
				.address(registerFoodTruckReq.getAddress())
				.workingDate(LocalDate.parse(dateDto.getWorkingDay(), DateTimeFormatter.ISO_DATE))
				.startTime(LocalDateTime.parse(dateDto.getWorkingDay() + " " + dateDto.getStartTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
				.endTime(LocalDateTime.parse(dateDto.getWorkingDay() + " " + dateDto.getEndTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
				.isValid(true).build();

			scheduleRepository.save(schedule);
		}
	}

	// 푸드트럭 수정
	public void updateFoodTruck(RegisterFoodTruckReq registerFoodTruckReq, User user) throws IllegalAccessException {
		// 푸드트럭 찾기
		FoodTruck foodTruck = foodTruckRepository.findByUser(user)
			.orElseThrow(NoSuchElementException::new);

		// 메뉴 삭제
		deleteMenu(foodTruck);

		// 새 메뉴 등록
		for(MenuDto menuDto : registerFoodTruckReq.getMenuList()){
			final Menu menu = Menu.builder()
				.name(menuDto.getName())
				.foodTruck(foodTruck)
				.price(menuDto.getPrice())
				.description(menuDto.getDescription())
				.src(menuDto.getSrc()).build();

			menuRepository.save(menu);
		}
		// 스케쥴 수정
//		Schedule schedule = scheduleRepository.findByFoodTruck(foodTruck)
//			.orElseThrow(NoSuchElementException::new);
//		schedule.update(registerFoodTruckReq);
//		scheduleRepository.save(schedule);

		// 푸드트럭 수정
		foodTruck.update(registerFoodTruckReq);
		foodTruckRepository.save(foodTruck);
	}

	// 메뉴 삭제
	public void deleteMenu(FoodTruck foodTruck){
		List<Menu> menuList = menuRepository.findAllByFoodTruck(foodTruck);
		for(Menu menu : menuList){
			try {
				menuRepository.delete(menu);
			} catch (DataIntegrityViolationException exception) {
				throw new IllegalArgumentException();
			}
		}
	}

	// 푸드트럭 리뷰 등록
	public void registerFoodTruckReview(RegisterFoodTruckReviewReq registerFoodTruckReviewReq, User user){
		// 주문내역에서 찾음
		Orders order = ordersRepository.findById(registerFoodTruckReviewReq.getOrdersId())
			.orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_ORDERS_ERROR_MESSAGE));
		// Review 에서 찾음 -> 에러 (테스트 코드 작성) - 주문 내역 1번에 1번의 리뷰만 달 수 있다.

		final Review review = Review.builder()
			.user(user)
			.orders(order)
			.content(registerFoodTruckReviewReq.getContent())
			.grade(registerFoodTruckReviewReq.getGrade())
			.src(registerFoodTruckReviewReq.getSrc())
			.build();
		reviewRepository.save(review);
	}

	// 푸드트럭 리뷰 조회
	public List<GetFoodTruckReviewRes> getFoodTruckReview(Integer foodTruckId){
		List<Review> findReviewList = reviewRepository.findAllByFoodTruckId(foodTruckId);
		List<GetFoodTruckReviewRes> reviewList = new ArrayList<>();
		System.out.println("리뷰 갯수 : " + findReviewList.size());

		for(Review review : findReviewList){

			reviewList.add(GetFoodTruckReviewRes.builder()
					.id(review.getId())
					.userId(review.getUser().getId())
					.ordersId(review.getOrders().getId())
					.content(review.getContent())
					.grade(review.getGrade())
					.src(review.getSrc())
					.regDate(review.getRegDate())
				.build());
		}
		return reviewList;
	}

	// 현재 위치와 가까운 푸드트럭 조회
	public List<GetNearFoodTruckRes> getNearFoodTruck(GetNearFoodTruckReq getNearFoodTruckReq){
		List<Schedule> scheduleList = scheduleRepository.findScheduleNearBy(getNearFoodTruckReq.getLat(),getNearFoodTruckReq.getLng());
		if(scheduleList.isEmpty())
			throw new IllegalArgumentException("스케줄을 찾을 수 없습니다.");

		List<GetNearFoodTruckRes> foodTruckList = new ArrayList<>();
		for(Schedule schedule : scheduleList) {
			// 카테고리에 해당하는 푸드트럭
			FoodTruck foodTruck = schedule.getFoodTruck();
			if(foodTruck == null)
				throw new IllegalArgumentException(NOT_FOUNT_FOODTRUCK_ERROR_MESSAGE);

			if(foodTruck.getCategory() != getNearFoodTruckReq.getCategory()) continue;

			List<Menu> menuList = menuRepository.findAllByFoodTruck(foodTruck);
			List<MenuDto> menuDtoList = new ArrayList<>();
			for(Menu menu : menuList){
				menuDtoList.add(MenuDto.of(menu));
			}

			Double grade = 0.0;
			List<Review> findReviewList = reviewRepository.findAllByFoodTruckId(foodTruck.getId());
			if(findReviewList.isEmpty()){
				throw new IllegalArgumentException("리뷰가 없습니다.");
			}

			for(Review r : findReviewList){
				grade += r.getGrade();
			}
			grade /= findReviewList.size();

			foodTruckList.add(GetNearFoodTruckRes.of(menuDtoList, foodTruck, schedule, grade));
		}

		return foodTruckList;
	}

	// 푸드트럭 검색
	public List<GetNearFoodTruckRes> searchFoodTruck(String keyword){
		// 키워드에 해당하는 푸드트럭 ID List 구하기
		List<Integer> foodTruckIdList = foodTruckRepository.findAllByKeyword(keyword);

		List<GetNearFoodTruckRes> foodTruckList = new ArrayList<>();

		// 푸드트럭 ID 에 해당하는 푸드트럭 정보들 리턴
		for(Integer foodTruckId : foodTruckIdList){
			FoodTruck foodTruck = foodTruckRepository.findById(foodTruckId)
				.orElseThrow(() -> new IllegalArgumentException(NOT_FOUNT_FOODTRUCK_ERROR_MESSAGE));

			List<Menu> menuList = menuRepository.findAllByFoodTruck(foodTruck);
			List<MenuDto> menuDtoList = new ArrayList<>();
			for(Menu menu : menuList){
				menuDtoList.add(MenuDto.of(menu));
			}

			Double grade = 0.0;
			List<Review> findReviewList = reviewRepository.findAllByFoodTruckId(foodTruckId);
			for(Review r : findReviewList){
				grade += r.getGrade();
			}
			grade /= findReviewList.size();

			Schedule schedule = scheduleRepository.findScheduleByFoodTruckAndDate(foodTruckId).orElse(null);
//			if(schedule == null) 오늘은 운영시간이 아닙니다. 테스트 케이스 작성

			foodTruckList.add(GetNearFoodTruckRes.of(menuDtoList, foodTruck, schedule, grade));
		}
		return foodTruckList;
	}

	public FoodTruck getFoodTruckByUser(User user){
		return foodTruckRepository.findByUser(user).orElse(null);
	}

	@Transactional
	public void saveFile(int ceoId, MultipartFile files) throws IOException {

		Optional<User> user = userRepository.findById(ceoId);

		if(!user.isPresent()) {
			return;
		}

		Optional<FoodTruck> foodTruck = foodTruckRepository.findByUser(user.get());

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
		FileEntity file = FileEntity.builder()
			.orgNm(origName)
			.savedNm(savedName)
			.savedPath(savedPath)
			.foodTruck(foodTruck.get())
			.build();

		// 실제로 로컬에 uuid를 파일명으로 저장
		files.transferTo(new File(savedPath));

		foodTruck.get().setFileEntity(file);
	}

	public FileEntity getFile(int ceoId) {
		Optional<User> user = userRepository.findById(ceoId);
		if(!user.isPresent()){
			return null;
		}

		Optional<FoodTruck> foodTruck = foodTruckRepository.findByUser(user.get());
		if(!foodTruck.isPresent()){
			return null;
		}

		return foodTruck.get().getFileEntity();
	}
}
