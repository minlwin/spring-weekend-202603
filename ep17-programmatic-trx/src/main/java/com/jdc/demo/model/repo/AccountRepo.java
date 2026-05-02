package com.jdc.demo.model.repo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.demo.model.output.AccountInfo;

@Repository
public class AccountRepo {
	
	@Autowired
	private JdbcClient client;
	
	@Value("${app.sql.account.find-by-code}")
	private String findById;
	@Value("${app.sql.account.update}")
	private String update;

	@Transactional(readOnly = true)
	public Optional<AccountInfo> findById(String code) {
		return client.sql(findById)
				.param("code", code)
				.query(AccountInfo.class)
				.optional();
	}

	@Transactional
	public void update(String code, int amount) {
		client.sql(update)
			.param("code", code)
			.param("amount", amount)
			.update();
	}

}
