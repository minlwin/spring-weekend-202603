package com.jdc.di;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanConfigTest {

	@Test
	void testConfig() {
		try(var context = new ClassPathXmlApplicationContext(
				"/application.xml",
				"/default-config.xml",
				"/custom-config.xml")) {
			
			var service = context.getBean(MyService.class);
			assertNotNull(service);
			
			service.showMessage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
