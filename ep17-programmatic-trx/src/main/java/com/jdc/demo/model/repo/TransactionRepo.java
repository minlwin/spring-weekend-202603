package com.jdc.demo.model.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.demo.model.input.TransactionForm;

@Repository
public class TransactionRepo {
	
	@Autowired
	private JdbcClient client;
	
	@Value("${app.sql.transaction.create}")
	private String create;

	@Transactional
	public void create(TransactionForm form) {
		client.sql(create)
			.paramSource(form)
			.update();
	}

}
