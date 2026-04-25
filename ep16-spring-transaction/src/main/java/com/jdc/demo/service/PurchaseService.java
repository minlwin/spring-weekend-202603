package com.jdc.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jdc.demo.domains.input.PurchaseForm;
import com.jdc.demo.domains.output.PurchaseDetails;

@Service
public class PurchaseService {

	public int purchase(PurchaseForm form) {
		return 0;
	}
	
	public Optional<PurchaseDetails> findById(int id) {
		return null;
	}
}
