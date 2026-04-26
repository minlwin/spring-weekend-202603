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
import com.jdc.demo.domains.input.SaleForm;
import com.jdc.demo.domains.output.SaleDetails;
import com.jdc.demo.service.SaleService;

@SpringBootTest
@Sql(
	scripts = "classpath:/test_data.sql", 
	executionPhase = ExecutionPhase.BEFORE_TEST_METHOD
)
public class SaleServiceTest {

	@Autowired
	private SaleService service;
	
	@ParameterizedTest
	@MethodSource("com.jdc.demo.provider.SaleServiceTestProvider#test_error_no_items")
	void test_error_no_items(SaleForm form, String message) {
		var exception = assertThrows(AppBusinessException.class, () -> service.sale(form));
		assertEquals(message, exception.getMessage());
	}

	@ParameterizedTest
	@MethodSource("com.jdc.demo.provider.SaleServiceTestProvider#test_error_no_enoungh_items")
	void test_error_no_enoungh_items(SaleForm form, String message) {
		var exception = assertThrows(AppBusinessException.class, () -> service.sale(form));
		assertEquals(message, exception.getMessage());
	}

	@ParameterizedTest
	@MethodSource("com.jdc.demo.provider.SaleServiceTestProvider#test_error_minus_delivery_fees")
	void test_error_minus_delivery_fees(SaleForm form, String message) {
		var exception = assertThrows(AppBusinessException.class, () -> service.sale(form));
		assertEquals(message, exception.getMessage());
	}

	@ParameterizedTest
	@MethodSource("com.jdc.demo.provider.SaleServiceTestProvider#test_error_minus_quantity")
	void test_error_minus_quantity(SaleForm form, String message) {
		var exception = assertThrows(AppBusinessException.class, () -> service.sale(form));
		assertEquals(message, exception.getMessage());
	}
		
	@ParameterizedTest
	@MethodSource("com.jdc.demo.provider.SaleServiceTestProvider#test_error_minus_discount")
	void test_error_minus_discount(SaleForm form, String message) {
		var exception = assertThrows(AppBusinessException.class, () -> service.sale(form));
		assertEquals(message, exception.getMessage());
	}

	@ParameterizedTest
	@MethodSource("com.jdc.demo.provider.SaleServiceTestProvider#test_error_large_discount")
	void test_error_large_discount(SaleForm form, String message) {
		var exception = assertThrows(AppBusinessException.class, () -> service.sale(form));
		assertEquals(message, exception.getMessage());
	}

	@ParameterizedTest
	@MethodSource("com.jdc.demo.provider.SaleServiceTestProvider#test_success")
	void test_success(String testCase, SaleForm form, SaleDetails expected) {
		var saleId = service.sale(form);
		var saleDetails = service.findById(saleId);
		assertTrue(saleDetails.isPresent());
		assertEquals(expected, saleDetails.get());
	}
}
