package com.jdc.demo.repo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.jdc.demo.domains.input.PurchaseForm;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PurchaseRepo {
	
	private final JdbcClient jdbcClient;
	
	@Value("${app.sql.purchase.create}")
	private String create;

	public int create(PurchaseForm form) {
		var keyHolder = new GeneratedKeyHolder();
		
		jdbcClient.sql(create)
			.param("employeeId", form.employeeId())
			.param("supplierId", form.supplierId())
			.param("transportationFees", form.transportationFees())
			.param("purchaseDate", form.purchaseDate())
			.update(keyHolder);
		
		return keyHolder.getKey().intValue();
	}

}
