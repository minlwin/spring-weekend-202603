package com.jdc.bean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserCreationTest {

	@Test
	void testCreateUser() {
		try (var context = new ClassPathXmlApplicationContext("/application.xml")) {
			var user = context.getBean("user", User.class);
			assertNotNull(user);
			assertEquals("Aung Aung", user.getName());
			assertEquals("09782003098", user.getPhone());
			assertEquals("aung@gmail.com", user.getEmail());

			var defaultBean = context.getBean("defaultUser", User.class);
			assertNotNull(defaultBean);
			assertNull(defaultBean.getName());
			assertNull(defaultBean.getPhone());
			assertNull(defaultBean.getEmail());

			var thidar = context.getBean("thidar", User.class);
			assertNotNull(thidar);
			assertEquals("Thidar", thidar.getName());
			assertNull(thidar.getPhone());
			assertNull(thidar.getEmail());

			var nilar = context.getBean("nilar", User.class);
			assertNotNull(nilar);
			assertEquals("Nilar", nilar.getName());
			assertEquals(user.getPhone(), nilar.getPhone());
			assertEquals(user.getEmail(), nilar.getEmail());
		}
	}
}
