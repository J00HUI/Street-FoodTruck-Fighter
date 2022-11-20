package com.ssafy.foodtruck.model.service;

import com.ssafy.foodtruck.db.entity.*;
import com.ssafy.foodtruck.db.repository.BusinessRepository;
import com.ssafy.foodtruck.db.repository.FoodtruckRepository;
import com.ssafy.foodtruck.db.repository.MenuRepository;
import com.ssafy.foodtruck.dto.response.BusinessRes;
import com.ssafy.foodtruck.dto.response.GetBusinessRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("businessService")
@RequiredArgsConstructor
public class BusinessService {

	private final FoodtruckRepository foodtruckRepository;
	private final BusinessRepository businessRepository;
	private final MenuRepository menuRepository;

	public void registerBusiness(User user) {

		Optional<FoodTruck> foodTruck = foodtruckRepository.findByUser(user);
		if(!foodTruck.isPresent()){
			return;
		}


		// 오늘 정산이 이미 존재한다면 다 지우고 다시 정산!
		businessRepository.deleteAllByFoodTruck(foodTruck.get().getId());

		//각각 menuList, countList를 menuId를 기준으로 정렬해서 가져온다.
		List<Integer> menuList = businessRepository.findMenuIdByFoodTruckId(foodTruck.get().getId());

		List<Integer> countList = businessRepository.findCountByFoodTruckId(foodTruck.get().getId());

		//빈 값이면 데이터 넣어줄 필요 없음
		if(menuList.size() == 0){
			return;
		}

		//서로 데이터 양이 다르면 안됨
		if(menuList.size() != countList.size()){
			return;
		}

		for(int i=0; i<menuList.size(); i++){

			// 해당 번호의 메뉴가 존재하지 않는다면
			Optional<Menu> menu = menuRepository.findById(menuList.get(i));
			if(!menu.isPresent()){
				return;
			}

			Business business = Business.builder()
				.foodTruck(foodTruck.get())
				.menu(menu.get())
				.revenue(menu.get().getPrice() * countList.get(i))
				.build();

			businessRepository.save(business);

		}

	}

	public List<GetBusinessRes> getBusinuess(User user) {
		Optional<FoodTruck> foodTruckOpt = foodtruckRepository.findByUser(user);
		if(!foodTruckOpt.isPresent()){
			return null;
		}

		Optional<List<Business>> businessListOpt = businessRepository.findBusinessByFoodtruckId(foodTruckOpt.get().getId());
		if(!businessListOpt.isPresent()){
			return null;
		}

		List<BusinessRes> businessResList = new ArrayList<>();

		for(Business business : businessListOpt.get()) {
			businessResList.add(BusinessRes.of(business));
		}

		List<GetBusinessRes> getBusinessResList = new ArrayList<>();

		//쭉 돌면서 새로운 날짜가 나왔을 경우 새로운 객체를 생성해주고 그 객체 안의 리스트에 정보를 넣어준다.
		for(BusinessRes businessRes : businessResList) {
			//처음 들어왔을 때는
			if(getBusinessResList.size() == 0) {

				//새로운 GetBusinessRes 생성해서 리스트에 넣어준다.
				getBusinessResList.add(GetBusinessRes.builder()
						.regDate(businessRes.getRegDate())
						.businessResList(new ArrayList<>())
						.build());

				getBusinessResList.get(0).getBusinessResList().add(businessRes);
				continue;
			}

			// 값이 다르면
			if(!getBusinessResList.get(getBusinessResList.size()-1).getRegDate().equals(businessRes.getRegDate())) {

				//새로운 GetBusinessRes 생성해서 리스트에 넣어준다.
				getBusinessResList.add(GetBusinessRes.builder()
						.regDate(businessRes.getRegDate())
						.businessResList(new ArrayList<>())
						.build());

				getBusinessResList.get(getBusinessResList.size()-1).getBusinessResList().add(businessRes);
				continue;
			}

			// 값이 같을때
			getBusinessResList.get(getBusinessResList.size()-1).getBusinessResList().add(businessRes);
		}


		return getBusinessResList;
	}
}
