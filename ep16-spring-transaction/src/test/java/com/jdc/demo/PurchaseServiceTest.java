package com.jdc.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.jdc.demo.domains.AppBusinessException;
import com.jdc.demo.domains.input.PurchaseForm;
import com.jdc.demo.domains.output.PurchaseDetails;
import com.jdc.demo.service.PurchaseService;

@SpringBootTest
@Sql(
	scripts = "classpath:/test_data.sql", 
	executionPhase = ExecutionPhase.BEFORE_TEST_METHOD
)
class PurchaseServiceTest {
	
	@Autowired
	private PurchaseService service;
	
	@ParameterizedTest
	@MethodSource("com.jdc.demo.provider.PurchaseServiceTestProvider#test_error_future_date")
	void test_error_future_date(PurchaseForm form, String message) {
		var exception = assertThrows(AppBusinessException.class, () -> service.purchase(form));
		assertEquals(message, exception.getMessage());
	}
	
	@ParameterizedTest
	@MethodSource("com.jdc.demo.provider.PurchaseServiceTestProvider#test_error_empty_items")
	void test_error_empty_items(PurchaseForm form, String message) {
		var exception = assertThrows(AppBusinessException.class, () -> service.purchase(form));
		assertEquals(message, exception.getMessage());
	}
	
	@ParameterizedTest
	@MethodSource("com.jdc.demo.provider.PurchaseServiceTestProvider#test_error_minus_price")
	void test_error_minus_price(PurchaseForm form, String message) {
		var exception = assertThrows(AppBusinessException.class, () -> service.purchase(form));
		assertEquals(message, exception.getMessage());
	}
	
	@ParameterizedTest
	@MethodSource("com.jdc.demo.provider.PurchaseServiceTestProvider#test_success")
	void test_success(String testPattern, PurchaseForm form, PurchaseDetails expected) {
		var id = service.purchase(form);
		var details = service.findById(id);
		assertTrue(details.isPresent());
		assertEquals(expected, details.get());
	}
}
