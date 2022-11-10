package com.ssafy.foodtruck.controller;

import com.ssafy.foodtruck.db.entity.FoodTruck;
import com.ssafy.foodtruck.db.entity.User;
import com.ssafy.foodtruck.db.entity.UserType;
import com.ssafy.foodtruck.dto.JWTokenDto;
import com.ssafy.foodtruck.dto.UserReq;
import com.ssafy.foodtruck.dto.response.LoginCeoRes;
import com.ssafy.foodtruck.model.service.AuthService;
import com.ssafy.foodtruck.model.service.FoodTruckService;
import com.ssafy.foodtruck.model.service.UserService;
import com.ssafy.foodtruck.util.JWToken;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

import javax.servlet.http.HttpServletResponse;

/**
 * 인증 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "인증 API", tags = {"Auth."})
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;
	private final UserService userService;
	private final FoodTruckService foodTruckService;

    @PostMapping("/login")
    @ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserReq.class),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> login(@RequestBody @ApiParam(value = "로그인 정보", required = true) UserReq userReq, HttpServletResponse resp) {
        JWToken jwt = authService.login(userReq);

		// 로그인한 사용자가 CEO
		if(userReq.getUserType() == UserType.CEO){
			User user = userService.getUserByEmail(userReq.getEmail());
			FoodTruck foodTruck = foodTruckService.getFoodTruckByUser(user);
			return new ResponseEntity<>(LoginCeoRes.of(jwt, foodTruck.getId()), HttpStatus.OK);
		}
//        ResponseCookie cookie = ResponseCookie.from("refresh-token", jwt.getRefreshToken())
//                .maxAge(60*60*24*15)
//                .httpOnly(true)
//                .secure(true)
//                .domain("")
//                .path("/")
//                .sameSite("None")
//                .build();
//
//        resp.setHeader("Set-Cookie", cookie.toString());
        return new ResponseEntity<>(JWTokenDto.of(jwt), HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(@CookieValue(value="refresh-token", required = false) String refreshToken, HttpServletResponse resp) {
        authService.logout(refreshToken);
//        ResponseCookie cookie = ResponseCookie.from("refresh-token",null)
//                .maxAge(0)
//                .httpOnly(true)
//                .secure(true)
//                .domain("")
//                .path("/")
//                .sameSite("None")
//                .build();
//
//        resp.setHeader("Set-Cookie",cookie.toString());
        return new ResponseEntity<>("logout success", HttpStatus.OK);
    }

//    //api docs에 추가해야겠다.
//    //프론트에서 access token 확인한 뒤에 만료됐다면 다시 신청해줘야함.
//    @GetMapping("/reissue")
//    public ResponseEntity<?> reissue(@CookieValue(value="refresh-token", required = false) String refreshToken){
//        JWToken jwt = authService.reissue(refreshToken);
//        return response.success(JWTokenDto.of(jwt));
//    }

//    @GetMapping("/oauth2/{type}")
//    public void socialLogin(@PathVariable String type) throws IOException {
//        oAuthService.request(SocialLoginType.valueOf(type.toUpperCase()));
//    }
//
//    @GetMapping("/oauth2/{type}/callback")
//    public void callback(@PathVariable String type, @RequestParam String code){
//        oAuthService.oauthLogin(SocialLoginType.valueOf(type.toUpperCase()),code);
//    }
}
