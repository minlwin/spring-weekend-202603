package com.jdc.demo.repo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SupplierRepo {

	private final JdbcClient jdbcClient;
	
	@Value("${app.sql.supplier.is-present}")
	private String isPresent;

	@Transactional(readOnly = true)
	public boolean isPresent(int id) {
		return jdbcClient
				.sql(isPresent)
				.param("id", id)
				.query(Long.class)
				.single() > 0;		
	}

}
