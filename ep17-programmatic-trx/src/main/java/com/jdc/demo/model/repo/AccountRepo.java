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

	@Transactional(readOnly = true)
	public Optional<AccountInfo> findById(String code) {
		return client.sql(findById)
				.param("code", code)
				.query(AccountInfo.class)
				.optional();
	}

}
