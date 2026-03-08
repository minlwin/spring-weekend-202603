package com.jdc.bean;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanScopeTest {

	@Test
	void test() {

		try (var context = new ClassPathXmlApplicationContext()) {
			context.setConfigLocation("/application.xml");
			context.refresh();

			var wrapper = context.getBean(UserWrapper.class);
			assertEquals("Hello! I am Aung Aung.", wrapper.greet());
		}
	}
}
