package com.ssafy.foodtruck.model.service;

import com.ssafy.foodtruck.db.entity.*;
import com.ssafy.foodtruck.db.repository.*;
import com.ssafy.foodtruck.dto.MenuDto;
import com.ssafy.foodtruck.dto.request.RegisterFoodTruckReq;
import com.ssafy.foodtruck.dto.request.RegisterFoodTruckReviewReq;
import com.ssafy.foodtruck.dto.response.GetFoodTruckRes;
import com.ssafy.foodtruck.dto.response.GetFoodTruckReviewRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.ssafy.foodtruck.constant.FoodTruckConstant.*;

@Service("foodTruckService")
@RequiredArgsConstructor
@Transactional
public class FoodTruckService {

	private final FoodTruckRepository foodTruckRepository;
	private final ScheduleRepository scheduleRepository;
	private final MenuRepository menuRepository;
	private final OrdersRepository ordersRepository;
	private final ReviewRepository reviewRepository;

	// 푸드트럭 정보 조회
	public GetFoodTruckRes getFoodTruck(Integer foodTruckId){

		FoodTruck foodTruck = foodTruckRepository.findById(foodTruckId)
			.orElseThrow(() -> new IllegalArgumentException(NOT_FOUNT_FOODTRUCK_ERROR_MESSAGE));

		Schedule schedule = scheduleRepository.findByFoodTruck(foodTruck).orElse(null);
//		if(schedule == null) throw Error?

		List<MenuDto> list = new ArrayList<>();
		Double grade = 0.0;
		Integer numberOfPeople = 0;
		Integer time = 0;

		return GetFoodTruckRes.of(list, foodTruck, schedule, grade, numberOfPeople, time);
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
			.src(registerFoodTruckReq.getSrc())
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
		final Schedule schedule = Schedule.builder()
			.foodTruck(savedFoodTruck)
			.latitude(registerFoodTruckReq.getLatitude())
			.longitude(registerFoodTruckReq.getLongtitue())
			.address(registerFoodTruckReq.getAddress())
			.startDate(LocalDateTime.parse(registerFoodTruckReq.getStart_date(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
			.endDate(LocalDateTime.parse(registerFoodTruckReq.getEnd_date(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
			.isValid(true).build();
		scheduleRepository.save(schedule);
	}

	// 푸드트럭 리뷰 등록
	public void registerFoodTruckReview(RegisterFoodTruckReviewReq registerFoodTruckReviewReq, User user){
		// 주문내역에서 찾음
		Orders order = ordersRepository.findById(registerFoodTruckReviewReq.getOrdersId())
			.orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_ORDERS_ERROR_MESSAGE));
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

		for(Review r : findReviewList){
			reviewList.add(GetFoodTruckReviewRes.builder()
				.content(r.getContent())
				.userId(r.getUser().getId())
				.ordersId(r.getOrders().getId())
				.grade(r.getGrade())
				.src(r.getSrc())
				.regDate(r.getRegDate())
				.id(r.getId()).build());

//			System.out.println(r.getContent() + ", " + r.getId() + "," + r.getGrade());
		}


		return reviewList;
//		EntityManager em = new EntityManager() {
//		}
//		em.createQuery("select c from Cup c where c.id in :cups")
//			.setParameter("cups", cups)
//			.getResultList();
	}

	public FoodTruck getFoodTruckByUser(User user){
		return foodTruckRepository.findByUser(user)
			.orElseThrow(() -> new IllegalArgumentException(NOT_FOUNT_FOODTRUCK_ERROR_MESSAGE));
	}
}
