package com.jdc.demo.domains.input;

public record PurchaseFormItem(
		int productId,
		int quantity,
		int unitPrice) {

}
