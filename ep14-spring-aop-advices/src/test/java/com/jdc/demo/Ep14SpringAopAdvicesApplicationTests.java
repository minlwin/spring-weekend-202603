package com.jdc.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jdc.demo.service.Calculator;

@SpringBootTest
class Ep14SpringAopAdvicesApplicationTests {
	
	@Autowired
	private Calculator calculator;

	@Test
	void contextLoads() {
		calculator.divide(10, 2);
		calculator.divide(10, 0);
	}

}
