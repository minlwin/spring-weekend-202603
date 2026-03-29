package com.jdc.demo.service;

import org.springframework.stereotype.Service;

@Service
public class MyService {

	public void showMessage() {
		System.out.println("MyService showMessage without argument");
	}
	
	public void showMessage(String name) {
		System.out.println("MyService showMessage with argument");
	}
	
	public int showMessageAndReturn() {
		System.out.println("MyService showMessageAndReturn");
		return 0; 
	}
}
