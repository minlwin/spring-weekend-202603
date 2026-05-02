package com.jdc.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import com.jdc.demo.model.BusinessException;
import com.jdc.demo.model.input.TransactionForm;
import com.jdc.demo.model.input.TransferForm;
import com.jdc.demo.model.output.TransferResult;
import com.jdc.demo.model.repo.TransactionRepo;
import com.jdc.demo.model.repo.TransactionTransferRepo;

@Service
public class TransferService {
	
	@Autowired
	private TransactionTemplate trxTemplate;
	@Autowired
	private AccountService accountService;
	@Autowired
	private TransactionIdGenerator idGenerator;
	@Autowired
	private TransactionRepo trxRepo;
	@Autowired
	private TransactionTransferRepo transferRepo;
	
	@Value("${app.limit.min-balance}")
	private int minBalance;

	public TransferResult transfer(TransferForm form) {
		
		var fromAccount = accountService.findByCode(form.accountFrom());
		var fromBalance = fromAccount.amount() - form.amount();
		
		if(fromBalance < minBalance) {
			throw new BusinessException("You have not enough amount to transfer.");
		}
		
		return trxTemplate.execute(_ -> {
			
			var toAccount = accountService.findByCode(form.accountTo());
			
			// Generate Transaction ID
			var trxId = idGenerator.next(LocalDate.now());
			
			// Create Transaction
			trxRepo.create(TransactionForm.builder()
					.trxId(trxId)
					.trxType("Transfer")
					.amount(form.amount())
					.issuer(form.accountFrom())
					.issueAt(LocalDateTime.now())
					.remark("Transfer to %s".formatted(toAccount.code()))
					.build());
			
			// Create Transfer Transaction
			transferRepo.create(trxId, form.accountTo());
			
			// Update From Account
			accountService.withdraw(fromAccount, form.amount());
			
			// Update To Account
			accountService.deposit(toAccount, form.amount());
			
			return new TransferResult(trxId, 
					"Transfer amount : %s from %s to %s".formatted(
						form.amount(),
						fromAccount.name(),
						toAccount.name()
					));
		});
	}
}
