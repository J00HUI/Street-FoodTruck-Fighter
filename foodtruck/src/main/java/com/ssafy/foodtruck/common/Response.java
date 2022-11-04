package com.ssafy.foodtruck.common;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import lombok.Builder;
import lombok.Getter;

@Component
public class Response {
	public ResponseEntity<?> success(HttpStatus status, String msg, Object data) {
		Body body = Body.builder()
			.code(status.value())
			.message(msg)
			.data(data)
			.isSuccess("true")
			.error(Collections.emptyList())
			.build();
		return ResponseEntity.ok(body);
	}

	public ResponseEntity<?> success(Object data) {
		return this.success(HttpStatus.OK, null, data);
	}

	public ResponseEntity<?> success(String msg){
		return this.success(HttpStatus.OK, msg, Collections.emptyList());
	}

	public ResponseEntity<?> fail(HttpStatus status, String msg, Object data){
		Body body = Body.builder()
			.code(status.value())
			.message(msg)
			.data(data)
			.isSuccess("false")
			.error(Collections.emptyList())
			.build();

		return ResponseEntity.ok(body);
	}

	public ResponseEntity<?> fail(String msg){
		return this.fail(HttpStatus.BAD_REQUEST, msg, Collections.emptyList());
	}

	@Getter
	@Builder
	static class Body {
		private String isSuccess;
		private int code;
		private String message;
		private Object data;
		private Object error;
	}
}
