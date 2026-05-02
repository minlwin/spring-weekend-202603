package com.jdc.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.jdc.demo.model.BusinessException;
import com.jdc.demo.model.input.TransferForm;
import com.jdc.demo.model.output.TransferResult;
import com.jdc.demo.service.AccountService;
import com.jdc.demo.service.TransferService;

@SpringBootTest
@Sql(scripts = "classpath:/test-data.sql")
public class TransferServiceTest {
	
	@Autowired
	private TransferService service;
	@Autowired
	private AccountService accountService;
	
	private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyyMMdd");

	@ParameterizedTest
	@CsvSource({
		"0001,0002,99501,You have not enough amount to transfer."
	})
	void test_error(String from, String to, int amount, String message) {
		var form = new TransferForm(from, to, amount);
		var exception = assertThrows(BusinessException.class, 
				() -> service.transfer(form));
		assertEquals(message, exception.getMessage());
	}
	
	@ParameterizedTest
	@MethodSource("test_success_src")
	void test_success(TransferForm form, TransferResult expected, int netFrom, int netTo) {
		var result = service.transfer(form);
		assertEquals(expected, result);
		
		var accountFrom = accountService.findByCode(form.accountFrom());
		assertEquals(netFrom, accountFrom.amount());
		
		var accountTo = accountService.findByCode(form.accountTo());
		assertEquals(netTo, accountTo.amount());
	}
	
	static Stream<Arguments> test_success_src() {
		return Stream.of(Arguments.of(
			new TransferForm("0001", "0002", 99500),
			new TransferResult("%s%04d".formatted(LocalDate.now().format(DF), 1), "Transfer amount : 99500 from Aung Aung to Thidar"),
			500,
			199500
		));
	}
}
