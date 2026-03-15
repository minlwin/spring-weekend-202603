package com.jdc.jdbc.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountRepoTest {

	@Autowired
	private AccountRepo repo;
	
	@Test
	void test_getAll() {
		assertNotNull(repo);
	}
}
