package com.jdc.demo.model.output;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record TransactionSeq(
		LocalDate trxDate,
		int seqNum) {
	
	private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyyMMdd");

	public TransactionSeq next() {
		return new TransactionSeq(trxDate, seqNum + 1);
	}

	public String code() {
		return "%s%04d".formatted(trxDate.format(DF), seqNum);
	}

}
