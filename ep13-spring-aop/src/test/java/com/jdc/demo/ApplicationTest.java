package com.jdc.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.jdc.demo.service.MyService;
import com.jdc.demo.service.OtherService;

@SpringBootTest
@ActiveProfiles("getting-start")
public class ApplicationTest {

	@Autowired
	private MyService service;
	
	@Autowired
	private OtherService otherService;
	
	@Test
	void test() {
		service.showMessage();
		
		service.showMessage("Testing");
		
		service.showMessageAndReturn();
		
		otherService.doJob();
	}
}
