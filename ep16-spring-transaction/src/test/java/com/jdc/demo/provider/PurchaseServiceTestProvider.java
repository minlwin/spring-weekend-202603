package com.jdc.demo.provider;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import com.jdc.demo.domains.input.PurchaseForm;
import com.jdc.demo.domains.input.PurchaseFormItem;

public class PurchaseServiceTestProvider {

	public static Stream<Arguments> test_error_future_date() {
		var form = PurchaseForm.builder()
				.purchaseDate(LocalDate.now().plusDays(1))
				.employeeId(1)
				.supplierId(1)
				.transportationFees(10000)
				.addItem(new PurchaseFormItem(1, 10, 10000))
				.build();
		
		return Stream.of(
			Arguments.of(form, "Purchase date should not be a future date.")
		);
	}

	public static Stream<Arguments> test_error_empty_items() {
		return Stream.of(
		);
	}

	public static Stream<Arguments> test_error_minus_price() {
		return Stream.of(
		);
	}
}
