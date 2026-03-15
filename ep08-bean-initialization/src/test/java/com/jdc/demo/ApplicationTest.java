package com.jdc.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jdc.demo.bean.ConstManager;

@SpringBootTest
public class ApplicationTest {
	
	@Autowired
	private ConstManager constManager;
	
	@Test
	void test_load() {
		assertNotNull(constManager);
		assertEquals("Top Up", constManager.getValue("trx01"));
	}
}
