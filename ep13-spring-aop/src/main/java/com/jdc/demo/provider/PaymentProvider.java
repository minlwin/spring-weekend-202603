package com.jdc.demo.provider;

import com.jdc.demo.annotation.Provider;

@Provider
public class PaymentProvider {

	public void paidFees(int amount) {
		System.out.println("PaymentProvider#paidFees(int)");
	}
	
	public void afterSuccessCallback(String message) {
		System.out.println("PaymentProvider#afterSuccessCallback(String)");
	}
}
