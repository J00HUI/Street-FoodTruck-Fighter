package com.ssafy.foodtruck.config;

import com.ssafy.foodtruck.auth.FoodtruckUserDetailService;
import com.ssafy.foodtruck.auth.JwtAuthenticationFilter;
import com.ssafy.foodtruck.exception.handler.JwtAccessDeniedHandler;
import com.ssafy.foodtruck.exception.handler.JwtAuthenticationEntryPoint;
import com.ssafy.foodtruck.model.service.UserService;
import com.ssafy.foodtruck.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * 인증(authentication) 와 인가(authorization) 처리를 위한 스프링 시큐리티 설정 정의.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final FoodtruckUserDetailService userDetailService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtAccessDeniedHandler accessDeniedHandler;
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;

	// Password 인코딩 방식에 BCrypt 암호화 방식 사용
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// DAO 기반으로 Authentication Provider를 생성
	// BCrypt Password Encoder와 UserDetailService 구현체를 설정
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		daoAuthenticationProvider.setUserDetailsService(this.userDetailService);
		return daoAuthenticationProvider;
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring()
			.antMatchers(
				"/",
				"/swagger-ui/**",
				"/swagger-resources/**",
				"/v2/api-docs/**",
				"/webjars/**",
				"/h2-console/**",
				"/favicon.com");
	}

	@Bean

	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.httpBasic().disable()
			.cors().configurationSource(corsConfigurationSource())
			.and()

			.csrf().disable()
			.exceptionHandling()
			.accessDeniedHandler(accessDeniedHandler)
			.authenticationEntryPoint(authenticationEntryPoint)
			.and()

			.headers()
			.frameOptions()
			.sameOrigin()
			.and()

			// 토큰 기반 인증이므로 세션 사용 하지않음
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()

			//HTTP 요청에 JWT 토큰 인증 필터를 거치도록 필터를 추가
			.addFilter(new JwtAuthenticationFilter(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), userService))
			.authorizeRequests()
			.antMatchers("/"
				, "/auth/login"
				, "/user/signup"
				, "/phone/sms"
				, "/phone"
				, "/foodtruck"
				, "/foodtruck/{foodtruck_id}"
				, "/foodtruck/search/{keyword}"
				, "/foodtruck/image/{foodtruck_id}"
				, "/review/{foodtruck_id}"
				, "/order/ceo/not/accepted/{ceo_id}"
				, "/order/ceo/accepted/{ceo_id}"
				, "/v1/pay/success"
				, "/menu/customer/{foodtruck_id}"
				, "/menu/image/{menu_id}"
					,"/v1/pay/success/{pg_token}"
			).permitAll()
			.anyRequest().authenticated(); //인증이 필요한 URL과 필요하지 않은 URL에 대하여 설정
		return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOriginPattern("*");
		configuration.addAllowedMethod("*");
		configuration.addAllowedHeader("*");
		configuration.addExposedHeader(JwtTokenUtil.HEADER_STRING);
		configuration.setAllowCredentials(true);
		configuration.setMaxAge(3600L);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
