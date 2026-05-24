package com.jdc.demo.entity.pk;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record InvoiceId(
		@Column(name = "invoice_at")
		LocalDate invoiceAt,
		@Column(name = "seq_number")
		int seqNumber) {

}
