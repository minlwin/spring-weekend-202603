package com.jdc.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jdc.demo.service.MyService;

@SpringBootTest
public class ApplicationTest {

	@Autowired
	private MyService service;
	
	@Test
	void test() {
		service.showMessage();
	}
}
