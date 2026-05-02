package com.jdc.demo.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionIdGenerator {

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String next(LocalDate trxDate) {
		return "";
	}
}
