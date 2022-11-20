package com.ssafy.foodtruck.model.service;

import com.ssafy.foodtruck.constant.OrdersErrorMessage;
import com.ssafy.foodtruck.db.entity.*;
import com.ssafy.foodtruck.db.repository.FoodtruckRepository;
import com.ssafy.foodtruck.db.repository.MenuRepository;
import com.ssafy.foodtruck.dto.request.MenuReq;
import com.ssafy.foodtruck.dto.request.RegisterMenuReq;
import com.ssafy.foodtruck.dto.request.UpdateMenuReq;
import com.ssafy.foodtruck.dto.response.GetMenuRes;
import com.ssafy.foodtruck.dto.response.MenuRes;
import com.ssafy.foodtruck.dto.response.RegisterMenuRes;
import com.ssafy.foodtruck.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MenuService {

	@Value("${file.dir}")
	private String fileDir;
	private final FoodtruckRepository foodTruckRepository;
	private final MenuRepository menuRepository;

	public List<RegisterMenuRes> registerMenu(RegisterMenuReq registerMenuReq, User user) throws IOException {

		List<RegisterMenuRes> list = new ArrayList<>();

		// user 에 해당하는 푸드트럭 조회
		FoodTruck foodTruck = foodTruckRepository.findByUser(user)
			.orElseThrow(() -> new NotFoundException(OrdersErrorMessage.NOT_FOUND_FOODTRUCK));

		for(int i=0; i<registerMenuReq.getMenuReqList().size(); i++){
			MenuReq menuReq = registerMenuReq.getMenuReqList().get(i);

			final Menu menu = Menu.builder()
				.name(menuReq.getName())
				.foodTruck(foodTruck)
				.price(menuReq.getPrice())
				.description(menuReq.getDescription())
				.build();
			menuRepository.save(menu);


			list.add(RegisterMenuRes.builder()
					.id(menu.getId())
					.description(menu.getDescription())
					.name(menu.getName())
					.price(menu.getPrice())
					.build());
		}
		return list;
	}

	@Transactional
	public void updateMenu(List<UpdateMenuReq> updateMenuReqList, User user) {
		for(UpdateMenuReq updateMenuReq : updateMenuReqList) {
			Optional<Menu> menuOptional = menuRepository.findById(updateMenuReq.getMenuId());

			//존재하지 않는다면 다음 요청 확인
			if(!menuOptional.isPresent()){
				continue;
			}

			//존재한다면 받은 값으로 수정
			Menu menu = menuOptional.get();
			menu.setDescription(updateMenuReq.getDescription());
			menu.setName(updateMenuReq.getName());
			menu.setPrice(updateMenuReq.getPrice());
		}
	}

	@Transactional
	public void saveMenuImg(Integer menuId, MultipartFile file) throws IOException {
		Optional<Menu> menu = menuRepository.findById(menuId);

		if(!menu.isPresent()) {
			return;
		}

		if (file.isEmpty()) {
			return;
		}

		// 원래 파일 이름 추출
		String origName = file.getOriginalFilename();

		// 파일 이름으로 쓸 uuid 생성
		String uuid = UUID.randomUUID().toString();

		// 확장자 추출(ex : .png)
		String extension = origName.substring(origName.lastIndexOf("."));

		// uuid와 확장자 결합
		String savedName = uuid + extension;

		// 파일을 불러올 때 사용할 파일 경로
		String savedPath = fileDir + savedName;

		// 파일 엔티티 생성
		MenuImg menuImg = MenuImg.builder()
			.orgNm(origName)
			.savedNm(savedName)
			.savedPath(savedPath)
//			.menu(menu.get())
			.build();

		// 실제로 로컬에 uuid를 파일명으로 저장
		file.transferTo(new File(savedPath));

		menu.get().setMenuImg(menuImg);
	}

	public MenuImg getMenuImg(int menuId) {

		Optional<Menu> menuOpt = menuRepository.findById(menuId);

		if (!menuOpt.isPresent()) {
			return null;
		}

		return menuOpt.get().getMenuImg();
	}

    public List<GetMenuRes> getMenuList(Integer foodtruckId) {
		//푸드트럭 id로 푸드트럭 객체 가져오기
		Optional<FoodTruck> foodTruckOptional = foodTruckRepository.findById(foodtruckId);
		if(!foodTruckOptional.isPresent()){
			new RuntimeException("아쉽게도 메뉴가 없네요...");
		}

		//해당 푸드트럭 메뉴 가져오기
		List<Menu> menuList = menuRepository.findAllByFoodTruck(foodTruckOptional.get());
		List<GetMenuRes> list = new ArrayList<>();

		//Entity를 Dto로 변경
		for(Menu menu : menuList) {
			list.add(GetMenuRes.of(menu));
		}

		return list;
    }

	public void deleteMenu(Integer menuId, User user) {
		Optional<Menu> menuOptional = menuRepository.findById(menuId);

		//해당 menuId에 해당하는 메뉴가 없다면 종료
		if(!menuOptional.isPresent()){
			return;
		}

		Menu menu = menuOptional.get();

		//메뉴 주인이 아닐때는 종료
		if(menu.getFoodTruck().getUser().getId() != user.getId()){
			return;
		}

		menuRepository.deleteById(menuId);
	}

//
//	public void updateMenu(){
//		// 메뉴 삭제
//		deleteMenu(foodTruck);
//
//		// 새 메뉴 등록
//		for(MenuReq menuReq : registerFoodTruckReq.getMenuList()){
//			final Menu menu = Menu.builder()
//				.name(menuReq.getName())
//				.foodTruck(foodTruck)
//				.price(menuReq.getPrice())
//				.description(menuReq.getDescription())
//				.build();
//
//			menuRepository.save(menu);
//		}
//
//		// 메뉴 이미지 등록
//		Map<String, MultipartFile> menuImgList = registerFoodTruckReq.getMenuImgList();
//		menuImgList.forEach((menuName, file)->{
//			Optional<Menu> menu = menuRepository.findMenuByName(menuName);
//
//			if(!menu.isPresent()) {
//				return;
//			}
//
//			if (file.isEmpty()) {
//				return;
//			}
//
//			// 원래 파일 이름 추출
//			String origName = file.getOriginalFilename();
//
//			// 파일 이름으로 쓸 uuid 생성
//			String uuid = UUID.randomUUID().toString();
//
//			// 확장자 추출(ex : .png)
//			String extension = origName.substring(origName.lastIndexOf("."));
//
//			// uuid와 확장자 결합
//			String savedName = uuid + extension;
//
//			// 파일을 불러올 때 사용할 파일 경로
//			String savedPath = fileDir + savedName;
//
//			// 파일 엔티티 생성
//			MenuImg menuImg = MenuImg.builder()
//				.orgNm(origName)
//				.savedNm(savedName)
//				.savedPath(savedPath)
//				.menu(menu.get())
//				.build();
//
//			// 실제로 로컬에 uuid를 파일명으로 저장
//			try {
//				file.transferTo(new File(savedPath));
//			} catch (IOException e) {
//				throw new RuntimeException(e);
//			}
//
//			menu.get().setMenuImg(menuImg);
//		});
//	}


	// 메뉴 삭제
//	public void deleteMenu(FoodTruck foodTruck){
//		List<Menu> menuList = menuRepository.findAllByFoodTruck(foodTruck);
//		for(Menu menu : menuList){
//			try {
//				menuRepository.delete(menu);
//			} catch (DataIntegrityViolationException exception) {
//				throw new IllegalArgumentException();
//			}
//		}
//	}
}
