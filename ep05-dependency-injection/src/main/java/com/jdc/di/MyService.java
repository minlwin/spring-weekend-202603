package com.jdc.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MyService {

	@Autowired
	@Qualifier("custom")
	private MyClient client;
	
	public void showMessage() {
		System.out.println(client.greet());
	}
}
