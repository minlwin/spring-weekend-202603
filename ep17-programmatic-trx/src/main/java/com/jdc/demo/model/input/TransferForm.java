package com.jdc.demo.model.input;

public record TransferForm(
		String accountFrom,
		String accountTo,
		int amount) {

}
