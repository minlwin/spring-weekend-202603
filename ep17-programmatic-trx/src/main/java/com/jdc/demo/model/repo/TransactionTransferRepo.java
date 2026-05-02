package com.jdc.demo.model.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TransactionTransferRepo {

	@Autowired
	private JdbcClient client;
	
	@Value("${app.sql.transfer.create}")
	private String create;

	@Transactional
	public void create(String trxId, String receiver) {
		client.sql(create)
			.param("trxId", trxId)
			.param("receiver", receiver)
			.update();
	}

}
