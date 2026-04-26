package com.jdc.demo.repo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductRepo {

	private final JdbcClient jdbcClient;
	
	@Value("${app.sql.product.is-present}")
	private String isPresent;

	public boolean isPresent(int id) {
		return jdbcClient
				.sql(isPresent)
				.param("id", id)
				.query(Long.class)
				.single() > 0;		
	}

}
