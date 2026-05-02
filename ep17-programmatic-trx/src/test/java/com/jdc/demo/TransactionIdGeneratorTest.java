package com.jdc.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.jdc.demo.model.BusinessException;
import com.jdc.demo.service.TransactionIdGenerator;

@SpringBootTest
@Sql(scripts = "classpath:/test-data.sql")
public class TransactionIdGeneratorTest {

	@Autowired
	private TransactionIdGenerator idGenerator;
	
	private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyyMMdd");
	
	@Test
	void test_success() {
		var expected = "%s%04d".formatted(LocalDate.now().format(DF), 1);
		var code = idGenerator.next(LocalDate.now());
		assertEquals(expected, code);
	}
	
	@ParameterizedTest
	@MethodSource("test_error_source")
	void test_error(LocalDate date, String message) {
		
		var exception = assertThrows(BusinessException.class, 
				() -> idGenerator.next(date));
		
		assertEquals(message, exception.getMessage());
	}
	
	static Stream<Arguments> test_error_source() {
		return Stream.of(
			Arguments.of(LocalDate.now().minusDays(1), "Transaction date must be current date."),
			Arguments.of(LocalDate.now().plusDays(1), "Transaction date must be current date.")
		);
	}
}
