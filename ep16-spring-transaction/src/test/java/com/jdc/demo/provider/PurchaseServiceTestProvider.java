package com.jdc.demo.provider;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import com.jdc.demo.domains.input.PurchaseForm;
import com.jdc.demo.domains.input.PurchaseFormItem;
import com.jdc.demo.domains.output.PurchaseDetails;
import com.jdc.demo.domains.output.PurchaseItem;

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
		var form = PurchaseForm.builder()
				.purchaseDate(LocalDate.now().plusDays(1))
				.employeeId(1)
				.supplierId(1)
				.transportationFees(10000)
				.build();
		
		return Stream.of(
			Arguments.of(form, "There is no purchase item.")
		);
	}

	public static Stream<Arguments> test_error_minus_price() {
		var form = PurchaseForm.builder()
				.purchaseDate(LocalDate.now().plusDays(1))
				.employeeId(1)
				.supplierId(1)
				.transportationFees(10000)
				.addItem(new PurchaseFormItem(1, 10, -1))
				.build();
		
		return Stream.of(
			Arguments.of(form, "Purchase price should be positive number.")
		);
	}
	
	public static Stream<Arguments> test_success() {
		return Stream.of(
			Arguments.of("First Time", getForm(1), getResult(1)),
			Arguments.of("Price Up", getForm(2), getResult(2)),
			Arguments.of("Price Down", getForm(3), getResult(3))
		);
	}

	private static PurchaseForm getForm(int type) {
		var builder = PurchaseForm.builder()
				.purchaseDate(LocalDate.now())
				.employeeId(1)
				.supplierId(1)
				.transportationFees(10000);
		
		switch(type) {
		case 1 -> builder.addItem(new PurchaseFormItem(1, 100, 5000));
		case 2 -> builder.addItem(new PurchaseFormItem(2, 100, 2400));
		default -> builder.addItem(new PurchaseFormItem(3, 100, 2800));
		}
		
		return builder.build();
	}

	
	private static PurchaseDetails getResult(int type) {
		
		var builder = PurchaseDetails.builder()
				.id(2)
				.employeeId(1)
				.employeeName("Aung Aung")
				.supplierId(1)
				.supplierName("Aung Gabar")
				.supplierPhone("09-1122-2233")
				.supplierEmail("aunggabar@example.com")
				.purchaseDate(LocalDate.now())
				.transportationFee(10000);
		
		switch(type) {
		case 1 -> builder.addItem(PurchaseItem.builder()
				.productId(1)
				.version(1)
				.productName("Coke")
				.amount(100)
				.purchasePrice(5000)
				.salePrice(5500)
				.stock(100)
				.lastPurchasePrice(0)
				.lastSalePrice(0)
				.lastStock(0)
				.build());
		case 2 -> builder.addItem(PurchaseItem.builder()
				.productId(2)
				.version(2)
				.productName("Pepsi")
				.amount(100)
				.purchasePrice(2400)
				.salePrice(2640)
				.stock(200)
				.lastPurchasePrice(2000)
				.lastSalePrice(2200)
				.lastStock(100)
				.build());
		default -> builder.addItem(PurchaseItem.builder()
				.productId(3)
				.version(2)
				.productName("Nest Café")
				.amount(100)
				.purchasePrice(2800)
				.salePrice(3300)
				.stock(200)
				.lastPurchasePrice(3000)
				.lastSalePrice(3300)
				.lastStock(100)
				.build());
		}
		
		return builder.build();
	}
	
}
