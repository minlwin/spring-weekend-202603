package com.jdc.demo.repo;

import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepo {

	public void create() {
		System.out.println("PaymentRepo#create()");
	}
}
