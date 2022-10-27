package com.ssafy.foodtruck.db.repository;

import com.ssafy.foodtruck.db.entity.User;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class UserRepositoryTest {

    private final String WRONG_EMAIL = "wjeiofhfiojweof@mnviosdno.mfnweof";
    private final String CORRECT_EMAIL = "godhkekf24@inha.edu";

    private UserRepository userRepository;

    @Autowired
    UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    public void UserRepository가Null이아님() {
        MatcherAssert.assertThat(userRepository, is(notNullValue()));
    }

    @Test
    public void 잘못된이메일로호출했을때Null() {
        Optional<User> user = userRepository.findByEmail(WRONG_EMAIL);
        assertThat(user).isEmpty();
    }

    @Test
    public void 올바른이메일을호출했을때크기가1() {
        Optional<User> user = userRepository.findByEmail(CORRECT_EMAIL);
        assertThat(user).isNotEmpty();
    }
}
