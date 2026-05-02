package com.jdc.demo.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "app.sql")
public class SqlConfigProps {
	
	private Account account = new Account();
	private TransactionSeq transactionSeq = new TransactionSeq();
	
	@Data
	static class Account {
		private String findByCode;
	}
	
	@Data
	static class TransactionSeq {
		private String findById;
		private String create;
		private String update;
	}
}
