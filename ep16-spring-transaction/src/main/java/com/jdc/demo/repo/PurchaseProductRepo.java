package com.jdc.demo.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.demo.domains.input.PurchaseFormItem;
import com.jdc.demo.domains.output.PurchaseItem;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PurchaseProductRepo {

	private final JdbcClient jdbcClient;
	
	@Value("${app.sql.purchase-product.create}")
	private String create;
	
	@Value("${app.sql.purchase-product.find-by-purchase-id}")
	private String findByPurchaseId;
	
	@Transactional
	public void create(int id, int version, PurchaseFormItem item) {
		jdbcClient.sql(create)
			.param("purchaseId", id)
			.param("productId", item.productId())
			.param("version", version)
			.param("unitPrice", item.unitPrice())
			.param("quantity", item.quantity())
			.update();
	}

	@Transactional(readOnly = true)
	public List<PurchaseItem> findByPurchaseId(int id) {
		return jdbcClient.sql(findByPurchaseId)
			.param("purchaseId", id)
			.query(PurchaseItem.class)
			.list();
	}

}
