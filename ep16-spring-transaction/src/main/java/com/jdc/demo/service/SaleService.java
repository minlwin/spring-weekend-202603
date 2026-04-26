package com.jdc.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jdc.demo.domains.input.SaleForm;
import com.jdc.demo.domains.output.SaleDetails;

@Service
public class SaleService {

	public int sale(SaleForm form) {
		return 0;
	}
		
	public Optional<SaleDetails> findById(int id) {
		return null;
	}
}
