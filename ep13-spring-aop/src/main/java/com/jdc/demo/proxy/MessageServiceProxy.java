package com.jdc.demo.proxy;

public class MessageServiceProxy implements MessageService{

	private MessageService service;
	
	public MessageServiceProxy(MessageService service) {
		super();
		this.service = service;
	}

	@Override
	public void send(String message) {
		System.out.println("Start Sending Message");
		
		service.send(message);
		
		System.out.println("End Sending Message");
	}

}
