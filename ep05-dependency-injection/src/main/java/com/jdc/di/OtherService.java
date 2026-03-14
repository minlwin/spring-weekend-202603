package com.jdc.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OtherService {

	@Autowired
	private MyClient client;
	
	public void showMessage() {
		System.out.println(client.greet());
	}
	
}
