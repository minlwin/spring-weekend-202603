package com.jdc.shop.controller.management.output;

import java.util.UUID;

import com.jdc.shop.model.entity.InvoiceItem;

import lombok.Data;

@Data
public class InvoiceDetailsItem {

	private int seqNumber;
	private UUID productId;
	private String productName;
	private int unitPrice;
	private int quantity;
	private int subtotal;

	public static InvoiceDetailsItem from(InvoiceItem item) {
		var result = new InvoiceDetailsItem();
		result.seqNumber = item.getId().getSeqNumber();
		result.productId = item.getProduct().getId();
		result.productName = item.getProduct().getName();
		result.unitPrice = item.getUnitPrice();
		result.quantity = item.getQuantity();
		result.subtotal = item.getUnitPrice() * item.getQuantity();
		return result;
	}
}
