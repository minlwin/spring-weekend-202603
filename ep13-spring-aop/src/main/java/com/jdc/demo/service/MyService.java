package com.jdc.demo.service;

import org.springframework.stereotype.Service;

@Service
public class MyService {

	public void showMessage() {
		System.out.println("Hello Spring From Service");
	}
}
