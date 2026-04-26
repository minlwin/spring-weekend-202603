package com.jdc.demo.repo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import com.jdc.demo.domains.input.PurchaseFormItem;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PurchaseProductRepo {

	private final JdbcClient jdbcClient;
	
	@Value("${app.sql.purchase-product.create}")
	private String create;
	
	public void create(int id, int version, PurchaseFormItem item) {
		jdbcClient.sql(create)
			.param("purchaseId", id)
			.param("productId", item.productId())
			.param("version", version)
			.param("unitPrice", item.unitPrice())
			.param("quantity", item.quantity())
			.update();
	}

}
