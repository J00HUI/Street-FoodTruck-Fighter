package com.ssafy.foodtruck.util;

import com.ssafy.foodtruck.constant.OrdersErrorMessage;
import com.ssafy.foodtruck.exception.NotEqualException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Component
public class RedisUtil {

	private static String SUCCESS = "success";

	private StringRedisTemplate redisTemplate;

	private static StringRedisTemplate staticRedisTemplate;

	@Autowired
	public RedisUtil(StringRedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@PostConstruct
	private void initStatic() {
		staticRedisTemplate = this.redisTemplate;
	}

	public static void setDataExpired(String key, String value, long duration) {
		Duration expireDuration = Duration.ofSeconds(duration);
		staticRedisTemplate.opsForValue()
			.set(key, value, expireDuration);
	}

	public static String getData(String key) {
		return staticRedisTemplate.opsForValue()
			.get(key);
	}

	public static String validateData(String key, String value) {
		if (!getData(key).equals(value)) {
			throw new NotEqualException(OrdersErrorMessage.NOT_EQUAL_VALIDATION_TOKEN);
		} else {
			return SUCCESS;
		}
	}

	public boolean delete(String key) {
		return redisTemplate.delete(key);
	}

	public boolean haskey(String key) {
		return redisTemplate.hasKey(key);
	}

	public long getExpireTime(String key) {
		return redisTemplate.getExpire(key, TimeUnit.MILLISECONDS);
	}
}
