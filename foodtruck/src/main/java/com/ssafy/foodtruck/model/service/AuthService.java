package com.ssafy.foodtruck.model.service;

import com.ssafy.foodtruck.db.entity.User;
import com.ssafy.foodtruck.db.entity.UserType;
import com.ssafy.foodtruck.dto.request.UserReq;
import com.ssafy.foodtruck.exception.InvalidEmailAndPasswordException;
import com.ssafy.foodtruck.util.JWToken;
import com.ssafy.foodtruck.util.JwtTokenUtil;
import com.ssafy.foodtruck.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final JwtTokenUtil jwtTokenUtil;
	private final RedisUtil redisUtil;

	//response.success(JwtTokenUtil.getToken(loginInfo.getEmail()), "login success", HttpStatus.OK)

	public JWToken login(UserReq userReq) throws InvalidEmailAndPasswordException {
		// 로그인 요청한 유저로부터 입력된 패스워드 와 디비에 저장된 유저의 암호화된 패스워드가 같은지 확인.(유효한 패스워드인지 여부 확인)
		if (checkRightPw(userReq)) {
			// 유효한 패스워드가 맞는 경우, 로그인 성공으로 응답.(액세스 토큰을 포함하여 응답값 전달)
//            JwtTokenUtil.getToken(loginInfo.getEmail())
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userReq.getEmail(), userReq.getPassword());
			Authentication auth = authenticationManagerBuilder.getObject().authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(auth);
			return jwtTokenUtil.createToken(userReq, auth);
		}
		throw new InvalidEmailAndPasswordException();
	}

	public boolean checkRightPw(UserReq userDtoReq) {
		User user = userService.getUserByEmail(userDtoReq.getEmail());
		return passwordEncoder.matches(userDtoReq.getPassword(), user.getPassword());
	}

	public User getCeoUser(String email) {
		User user = userService.getUserByEmail(email);
		if (user.getUserType() == UserType.CEO) return user;
		return null;
	}

	public void logout(String refreshToken) {
//        redisUtil.delete(refreshToken);
	}

	public JWToken reissue(String refreshToken) {
		String email = jwtTokenUtil.getEmailFromRefreshToken(refreshToken);

		// email이 없을때
		if (email == null) {
			//예외처리
			return null;
		}

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return jwtTokenUtil.reissueAccessToken(email, auth);
	}
}
