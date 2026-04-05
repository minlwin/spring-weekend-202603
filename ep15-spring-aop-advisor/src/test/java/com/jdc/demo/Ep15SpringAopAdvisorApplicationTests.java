package com.jdc.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jdc.demo.service.MessageService;
import com.jdc.demo.service.TelecomService;

@SpringBootTest
class Ep15SpringAopAdvisorApplicationTests {
	
	@Autowired
	private MessageService messageService;
	@Autowired
	private TelecomService telecomService;

	@Test
	void contextLoads() {
		messageService.send("Hello");
		telecomService.paidBill(1000);
		telecomService.paidBill(0);
	}

}
