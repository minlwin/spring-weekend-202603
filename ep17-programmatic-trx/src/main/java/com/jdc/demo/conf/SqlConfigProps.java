package com.jdc.demo.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "app.sql")
public class SqlConfigProps {
	
	private Account account = new Account();
	private TransactionSeq transactionSeq = new TransactionSeq();
	private Transaction transaction = new Transaction();
	private TransactionTransfer transfer = new TransactionTransfer();
	
	@Data
	static class Account {
		private String findByCode;
		private String update;
	}
	
	@Data
	static class Transaction {
		private String create;
	}
	
	@Data
	static class TransactionTransfer {
		private String create;
	}

	@Data
	static class TransactionSeq {
		private String findById;
		private String create;
		private String update;
	}
}
