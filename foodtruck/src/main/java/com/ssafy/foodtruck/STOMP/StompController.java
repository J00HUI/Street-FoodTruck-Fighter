package com.ssafy.foodtruck.STOMP;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class StompController {
	@MessageMapping("/TTT")
	@SendTo("/topic/message")
	public String ttt(String message) throws Exception {
		return message;
	}
}
