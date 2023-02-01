package com.ssafy.foodtruck.controller;

import com.ssafy.foodtruck.db.entity.User;
import com.ssafy.foodtruck.dto.request.UserReq;
import com.ssafy.foodtruck.dto.response.UserRes;
import com.ssafy.foodtruck.exception.ExistingEmailException;
import com.ssafy.foodtruck.model.service.UserService;
import com.ssafy.foodtruck.util.JwtTokenUtil;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ssafy.foodtruck.constant.UserConstant.*;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "유저 API", tags = {"User"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/signup")
    @ApiOperation(value = "회원 가입", notes = "<strong>이메일와 패스워드</strong>를 통해 회원가입 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> signup(@RequestBody @ApiParam(value="회원가입 정보", required = true) UserReq userDtoReq) {
        try {
			userService.createUser(userDtoReq);
		} catch (ExistingEmailException ex){
			return new ResponseEntity<>(DUPLICATED_USER_ERROR_MESSAGE, HttpStatus.FORBIDDEN);
		}
        return new ResponseEntity<>(SIGNUP_SUCCESS, HttpStatus.CREATED);
    }

	@GetMapping("")
	@ApiOperation(value = "회원 본인 정보 조회", notes = "로그인한 회원 본인의 정보를 응답한다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "사용자 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String bearerToken) {
		User user = userService.getUserByEmail(jwtTokenUtil.getEmailFromBearerToken(bearerToken));
		UserRes userDtoRes = UserRes.builder()
//                .businessNumber(user.getBusinessNumber())
			.email(user.getEmail())
			.userType(user.getUserType())
			.id(user.getId())
			.nickname(user.getNickname())
			.phone(user.getPhone())
			.build();
		return new ResponseEntity<>(userDtoRes, HttpStatus.OK);
	}
}
