package com.jdc.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.jdc.demo.model.BusinessException;
import com.jdc.demo.model.output.AccountInfo;
import com.jdc.demo.service.AccountService;

@SpringBootTest
@Sql(scripts = "classpath:/test-data.sql")
class AccountServiceTest {
	
	@Autowired
	private AccountService service;

	@ParameterizedTest
	@CsvSource({
		"0000,There is no account with code 0000.",
		"0003,There is no account with code 0003.",
	})
	void test_error_not_found(String code, String message) {
		var exception = assertThrows(BusinessException.class, 
				() -> service.findByCode(code));
		assertEquals(message, exception.getMessage());
	}
	
	@ParameterizedTest
	@CsvSource({
		"0001,Aung Aung,100000",
		"0002,Thidar,100000",
	})
	void test_success(String code, String name, int amount) {
		var result = service.findByCode(code);
		assertNotNull(result);
		assertEquals(new AccountInfo(code, name, amount), result);
	}

}
