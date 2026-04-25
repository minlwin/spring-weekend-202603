package com.jdc.demo.provider;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import com.jdc.demo.domains.input.SaleForm;
import com.jdc.demo.domains.output.SaleDetails;
import com.jdc.demo.domains.output.SaleItem;

public class SaleServiceTestProvider {

	public static Stream<Arguments> test_error_no_items() {
		return Stream.of(
			Arguments.of(
				SaleForm.builder()
				.customerId(1)
				.discount(0)
				.deliveryFee(0)
				.build(),
				"There is no items for sale."
			)
		);
	}

	public static Stream<Arguments> test_error_no_enoungh_items() {
		return Stream.of(
			Arguments.of(
				SaleForm.builder()
				.customerId(1)
				.discount(0)
				.deliveryFee(0)
				.addItem(2, 101)
				.build(),
				"There is no enough items for sale."
			)
		);
	}

	public static Stream<Arguments> test_error_minus_delivery_fees() {
		return Stream.of(
			Arguments.of(
				SaleForm.builder()
				.customerId(1)
				.discount(0)
				.deliveryFee(-1)
				.addItem(2, 10)
				.build(),
				"Delivery fee should not be negative value."
			)
		);
	}

	public static Stream<Arguments> test_error_minus_quantity() {
		return Stream.of(
			Arguments.of(
				SaleForm.builder()
				.customerId(1)
				.discount(0)
				.deliveryFee(0)
				.addItem(2, -1)
				.build(),
				"Product Quantity should not be negative value."
			)
		);
	}
	
	public static Stream<Arguments> test_error_minus_discount() {
		return Stream.of(
			Arguments.of(
				SaleForm.builder()
				.customerId(1)
				.discount(-1)
				.deliveryFee(0)
				.addItem(2, 10)
				.build(),
				"Discount should not be negative value."
			)
		);
	}

	public static Stream<Arguments> test_error_large_discount() {
		return Stream.of(
			Arguments.of(
				SaleForm.builder()
				.customerId(1)
				.discount(2201)
				.deliveryFee(0)
				.addItem(2, 1)
				.build(),
				"Discount should not be greater than actual amount."
			)
		);
	}

	public static Stream<Arguments> test_success() {
		return Stream.of(
			Arguments.of("Normal", getInput(1), getResult(1)),
			Arguments.of("Sold Out", getInput(2), getResult(2))
		);
	}
	
	public static SaleForm getInput(int pattern) {
		var builder = SaleForm.builder()
			.customerId(1)
			.discount(1000)
			.deliveryFee(0);
		
		switch(pattern) {
		case 1 -> builder.addItem(2, 99);
		case 2 -> builder.addItem(2, 100);
		}
		
		return builder.build();
	}
	
	public static SaleDetails getResult(int pattern) {
		var builder = SaleDetails.builder()
				.customerId(1)
				.customerName("Min Aung")
				.taxRate(0.05)
				.deliveryFee(0)
				.discount(1000);

		switch(pattern) {
		case 1 -> builder.addItem(SaleItem.builder()
				.productId(2)
				.productName("Pepsi")
				.salePrice(2200)
				.quantity(99)
				.remains(1)
				.build());
		case 2 -> builder.addItem(SaleItem.builder()
				.productId(2)
				.productName("Pepsi")
				.salePrice(2200)
				.quantity(100)
				.remains(0)
				.build());
		}

		return builder.build();
	}
}
