package com.jdc.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Embeddable;

@Embeddable
public record InvoiceId(
		LocalDate invoiceDate,
		int seqNumber) {

}
