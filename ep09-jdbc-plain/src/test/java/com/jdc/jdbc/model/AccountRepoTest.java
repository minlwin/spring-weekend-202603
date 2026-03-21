package com.jdc.jdbc.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
public class AccountRepoTest {

	@Autowired
	private AccountRepo repo;
	
	@Test
	@Sql(scripts = "classpath:/base.sql")
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
	@Sql(scripts = "classpath:/base.sql")
	void test_findById(int id) {
		var account = repo.findById(id);
		assertNotNull(account);
		assertEquals(id, account.id());
		assertEquals("user%d".formatted(id), account.name());
		assertEquals("phone%d".formatted(id), account.phone());
		assertEquals("email%d".formatted(id), account.email());
	}
	
	@Test
	@Sql(scripts = "classpath:/base.sql")
	void test_findById_notFound() {
		var account = repo.findById(4);
		assertNull(account);
	}
	
	
	@ParameterizedTest
	@ValueSource(ints = {
		1, 2, 3	
	})
	@Sql(scripts = "classpath:/base.sql")
	void test_create_success(int num) {
		var result = repo.create(
				"name%d".formatted(num), 
				"phone%d".formatted(num), 
				"email%d".formatted(num));
		
		assertEquals(4, result);
	}
	
	@ParameterizedTest
	@CsvSource(value = {
		"1,Thidar,0911112222,thidar@gmail.com,1",
		"3,Nilar,0911112223,nilar@gmail.com,1",
		"4,Mike,0911112224,mike@gmail.com,0",
	})
	@Sql(scripts = "classpath:/base.sql")
	void test_update(int id, String name, String phone, String email, int expected) {
		var result = repo.update(id, name, phone, email);
		assertEquals(expected, result);
	}
	
	@ParameterizedTest
	@CsvSource(value = {
		"1,1", "3,1", "4,0"
	})
	@Sql(scripts = "classpath:/base.sql")
	void test_delete(int id, int expected) {
		var result = repo.deleteById(id);
		assertEquals(expected, result);
	}
	
}
