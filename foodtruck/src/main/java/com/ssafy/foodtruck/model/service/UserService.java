package com.ssafy.foodtruck.model.service;

import com.ssafy.foodtruck.db.entity.Authority;
import com.ssafy.foodtruck.db.entity.User;
import com.ssafy.foodtruck.db.repository.UserRepository;
import com.ssafy.foodtruck.dto.request.UserReq;
import com.ssafy.foodtruck.exception.ExistingEmailException;
import com.ssafy.foodtruck.util.JwtTokenUtil;
import com.ssafy.foodtruck.util.UserRole;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
@RequiredArgsConstructor
@Transactional
public class UserService {

//    @Value("${spring.servlet.multipart.location}")
//    private String root;

//    public static final String INFO = "INFO::";
//    private static final String HEART = "HEART";

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
//    private final RedisUtil redisUtil;

    public void createUser(UserReq userDtoReq) {
		// 아이디 중복 검사
		User user = userRepository.findByEmail(userDtoReq.getEmail()).orElse(null);
		if(user != null){
			throw new ExistingEmailException();
		}

        userRepository.save(User.builder()
                        .email(userDtoReq.getEmail())
                        .password(passwordEncoder.encode(userDtoReq.getPassword()))
                        .businessNumber(userDtoReq.getBusinessNumber())
                        .phone(userDtoReq.getPhone())
                        .nickname(userDtoReq.getNickname())
                        .userType(userDtoReq.getUserType())
                        .authorities(
                                Collections.singleton(Authority.builder()
                                        .authName(UserRole.ROLE_USER)
                                        .build())
                        )
                        .build());
    }

    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("존재하지 않는 이메일입니다.");
        }
        return user.get();
    }

    // Uid Id 구분 헷갈리네...
//    public UserDto getUserByUid(int uId){
//        UserDto userDto = userDao.selectUserByUid(uId);
//        if(userDto == null){
//            throw new UsernameNotFoundException("존재하지 않는 u_id입니다.");
//        }
//        return userDto;
//    }

    public void checkExistingEmail(String email) throws ExistingEmailException {
        User user = userRepository.findByEmail(email).get();
        if (user != null) {
            throw new ExistingEmailException();
        }
    }

    // 얘도 Uid, id 헷갈린다...
//    public int getUidFromBearerToken(String BearerToken) {
//        return userDao.selectUidByEmail(jwtTokenUtil.getEmailFromBearerToken(BearerToken));
//    }
}
