package com.jdc.demo.repo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.jdc.demo.domains.input.PurchaseForm;
import com.jdc.demo.domains.output.PurchaseInfo;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PurchaseRepo {
	
	private final JdbcClient jdbcClient;
	
	@Value("${app.sql.purchase.create}")
	private String create;
	@Value("${app.sql.purchase.find-by-id}")
	private String findById;

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

	public Optional<PurchaseInfo> findById(int id) {
		return jdbcClient.sql(findById)
				.param("id", id)
				.query(PurchaseInfo.class)
				.optional();
	}

}
