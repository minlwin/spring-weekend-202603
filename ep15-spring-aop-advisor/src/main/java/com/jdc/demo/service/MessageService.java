package com.jdc.demo.service;

import org.springframework.stereotype.Component;

@Component
public class MessageService {

	public void send(String message) {
		System.out.println("MessageService#send : %s".formatted(message));
	}
}
