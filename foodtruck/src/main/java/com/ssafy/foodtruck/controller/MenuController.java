package com.ssafy.foodtruck.controller;

import com.ssafy.foodtruck.db.entity.FoodtruckImg;
import com.ssafy.foodtruck.db.entity.MenuImg;
import com.ssafy.foodtruck.db.entity.User;
import com.ssafy.foodtruck.db.repository.UserRepository;
import com.ssafy.foodtruck.dto.request.RegisterMenuReq;
import com.ssafy.foodtruck.dto.request.UpdateMenuReq;
import com.ssafy.foodtruck.dto.response.RegisterMenuRes;
import com.ssafy.foodtruck.exception.NotFoundException;
import com.ssafy.foodtruck.model.service.FoodTruckService;
import com.ssafy.foodtruck.model.service.MenuService;
import com.ssafy.foodtruck.model.service.UserService;
import com.ssafy.foodtruck.util.JwtTokenUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import lombok.RequiredArgsConstructor;

import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.ssafy.foodtruck.constant.FoodtruckConstant.*;
import static com.ssafy.foodtruck.constant.MenuConstant.REGISTER_MENU_SUCCESS;

/**
 * 메뉴 관련 API 요청 처리를 위한 컨트롤러 정의
 */
@Api(value = "메뉴 API", tags = {"Menu"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {

	private final MenuService menuService;

	private final UserService userService;

	private final FoodTruckService foodTruckService;

	private final JwtTokenUtil jwtTokenUtil;
	private final UserRepository userRepository;

	// 메뉴 등록
	@PostMapping
	@ApiOperation(value = " 메뉴 등록", notes = "<strong>내 메뉴를 등록한다.</strong>")
	public ResponseEntity<?> registerMenu(@RequestHeader("Authorization") @ApiParam(value="Access Token", required = true) String bearerToken,
											   @RequestBody @ApiParam(value="메뉴 정보", required = true) RegisterMenuReq registerMenuReq) {

		User user = userService.getUserByEmail(jwtTokenUtil.getEmailFromBearerToken(bearerToken));

		try {
			List<RegisterMenuRes> list = menuService.registerMenu(registerMenuReq, user);
			return new ResponseEntity<>(list, HttpStatus.CREATED);
		} catch (NotFoundException ex) {
			return new ResponseEntity<>(NOT_FOUNT_FOODTRUCK_ERROR_MESSAGE, HttpStatus.BAD_REQUEST);
		} catch (IOException e){
			return new ResponseEntity<>(SAVE_IMAGE_ERROR_MESSAGE, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@PostMapping("/update")
	@ApiOperation(value = " 메뉴 수정", notes = "<strong>내 메뉴를 수정한다.</strong>")
	public ResponseEntity<?> updateMenu(@RequestHeader("Authorization") @ApiParam(value="Access Token", required = true) String bearerToken,
											   @RequestBody @ApiParam(value="메뉴 정보", required = true) List<UpdateMenuReq> updateMenuReqList) {

		User user = userService.getUserByEmail(jwtTokenUtil.getEmailFromBearerToken(bearerToken));
		menuService.updateMenu(updateMenuReqList, user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{menu_id}")
	@ApiOperation(value = " 메뉴 삭제", notes = "<strong>내 메뉴를 삭제한다.</strong>")
	public ResponseEntity<?> deleteMenu(@RequestHeader("Authorization") @ApiParam(value="Access Token", required = true) String bearerToken,
										@PathVariable("menu_id") Integer menuId) {

		User user = userService.getUserByEmail(jwtTokenUtil.getEmailFromBearerToken(bearerToken));
		menuService.deleteMenu(menuId, user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/customer/{foodtruck_id}")
	@ApiOperation(value = " 메뉴 조회", notes = "<strong>내 메뉴를 조회한다.</strong>")
	public ResponseEntity<?> getFoodTruck(@PathVariable("foodtruck_id") Integer foodtruckId) {
		return new ResponseEntity<>(menuService.getMenuList(foodtruckId), HttpStatus.OK);
	}


	@PostMapping("/upload")
	public ResponseEntity<HttpStatus> saveMenuImg(@RequestHeader("Authorization") String bearerToken,
												  @RequestHeader("menuId") Integer menuId,
												  @RequestParam("file") MultipartFile file) throws IOException {
		menuService.saveMenuImg(menuId, file);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/image/{menu_id}")
	public ResponseEntity<?> getMenuImg(@PathVariable("menu_id") Integer menuId) throws IOException{
		MenuImg file = menuService.getMenuImg(menuId);
		if(file == null) {
			return new ResponseEntity<>("해당 메뉴의 이미지 파일이 없습니다.", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(new UrlResource("file:" + file.getSavedPath()), HttpStatus.OK);
	}

}
