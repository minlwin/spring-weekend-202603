package com.jdc.demo.proxy;

public class DefaultMessageService implements MessageService{

	@Override
	public void send(String message) {
		System.out.println("Sending '%s'".formatted(message));
	}

}
