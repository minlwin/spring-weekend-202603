package com.jdc.demo.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.demo.model.BusinessException;
import com.jdc.demo.model.output.TransactionSeq;
import com.jdc.demo.model.repo.TransactionSeqRepo;

@Service
public class TransactionIdGenerator {
	
	@Autowired
	private TransactionSeqRepo repo;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String next(LocalDate trxDate) {
		
		if(!trxDate.equals(LocalDate.now())) {
			throw new BusinessException("Transaction date must be current date.");
		}
		
		var seq = repo.findById(trxDate).orElseGet(() -> {
			var newSeq = new TransactionSeq(trxDate, 0);
			repo.create(newSeq);
			return newSeq;
		});
		
		var nextSeq = seq.next();
		repo.update(nextSeq);
		
		return nextSeq.code();
	}
}
