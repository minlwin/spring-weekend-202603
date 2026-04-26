package com.jdc.demo.repo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
@Transactional
public class ProductSupplierRepo {
	
	private final JdbcClient jdbcClient;
	
	@Value("${app.sql.product-supplier.is-present}")
	private String isPresent;

	@Value("${app.sql.product-supplier.create}")
	private String create;

	@Transactional(readOnly = true)
	public boolean isPresent(int productId, int supplierId) {
		return jdbcClient
				.sql(isPresent)
				.param("productId", productId)
				.param("supplierId", supplierId)
				.query(Long.class)
				.single() > 0;		
	}
	
	public void create(int productId, int supplierId) {
		jdbcClient.sql(create)
			.param("productId", productId)
			.param("supplierId", supplierId)
			.update();
	}

	public void createIfNeed(int productId, int supplierId) {
		if(!isPresent(productId, supplierId)) {
			create(productId, supplierId);
		}
	}


}
