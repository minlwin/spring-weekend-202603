package com.jdc.jdbc.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountRepoTest {

	@Autowired
	private AccountRepo repo;
	
	@Test
	void test_getAll() {
		var result = repo.getAll();
		assertNotNull(result);
		assertEquals(3, result.size());
		
		var index = 0;
		
		for(var account : result) {
			index ++;
			assertEquals(index, account.id());
			assertEquals("user%d".formatted(index), account.name());
			assertEquals("phone%d".formatted(index), account.phone());
			assertEquals("email%d".formatted(index), account.email());
		}
	}
	
	@ParameterizedTest
	@ValueSource(ints = {
		1, 2, 3	
	})
	void test_findById(int id) {
		var account = repo.findById(id);
		assertNotNull(account);
		assertEquals(id, account.id());
		assertEquals("user%d".formatted(id), account.name());
		assertEquals("phone%d".formatted(id), account.phone());
		assertEquals("email%d".formatted(id), account.email());
	}
	
	@Test
	void test_findById_notFound() {
		var account = repo.findById(4);
		assertNull(account);
	}
}
