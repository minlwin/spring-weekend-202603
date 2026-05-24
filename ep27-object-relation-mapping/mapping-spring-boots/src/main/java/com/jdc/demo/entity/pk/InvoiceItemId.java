package com.jdc.demo.entity.pk;

import java.time.LocalDate;

import jakarta.persistence.Column;

public record InvoiceItemId(
		@Column(name = "invoice_at", insertable = false, updatable = false)
		LocalDate invoiceAt,
		@Column(name = "seq_number", insertable = false, updatable = false)
		int seqNumber,
		@Column(name = "product_id", insertable = false, updatable = false)
		int productId) {

}
