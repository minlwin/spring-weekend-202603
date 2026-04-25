package com.jdc.demo.service;

import org.springframework.stereotype.Component;

import com.jdc.demo.advisor.LogEnable;

@Component
public class TelecomService {

	@LogEnable
	public void paidBill(int amount) {
		
		if(amount <= 0) {
			throw new IllegalArgumentException("Amount should not be zero ro negative value.");
		}
		
		System.out.println("TelecomService#paidBill : %s".formatted(amount));
	}
}
