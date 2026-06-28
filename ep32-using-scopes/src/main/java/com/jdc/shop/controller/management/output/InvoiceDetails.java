package com.jdc.shop.controller.management.output;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.jdc.shop.model.entity.Invoice;
import com.jdc.shop.model.entity.Invoice.Status;

import lombok.Data;

@Data
public class InvoiceDetails {

	private UUID id;
	private UUID customerId;
	private String customerName;
	private String customerEmail;
	private String customerPhone;
	private LocalDateTime invoiceAt;
	private Status status;
	private String address;

	private List<InvoiceDetailsItem> items;

	public static InvoiceDetails from(Invoice invoice) {
		var result = new InvoiceDetails();
		result.id = invoice.getId();
		var customer = invoice.getCustomer();
		result.customerId = customer.getId();
		result.customerName = customer.getName();
		result.customerEmail = customer.getAccount().getEmail();
		result.customerPhone = customer.getPhone();
		result.invoiceAt = invoice.getInvoiceAt();
		result.status = invoice.getStatus();
		result.address = invoice.getAddress();
		result.items = invoice.getItems().stream()
				.map(InvoiceDetailsItem::from)
				.toList();
		return result;
	}
	
	public int getTotal() {
		if(items == null) {
			return 0;
		}
		
		return items.stream().mapToInt(a -> a.getSubTotal()).sum();
	}
}
