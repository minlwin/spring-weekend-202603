package com.jdc.demo.repo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.demo.domains.output.StockDto;
import com.jdc.demo.domains.output.StockHistoryDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class StockRepo {
	
	private final JdbcClient jdbcClient;
	
	@Value("${app.sql.stock.find-by-id}")
	private String findById;
	@Value("${app.sql.stock.update}")
	private String update;
	
	@Transactional(readOnly = true)
	public StockDto findById(int productId) {
		return jdbcClient.sql(findById)
			.param("productId", productId)
			.query(new DataClassRowMapper<>(StockDto.class))
			.single();
	}

	@Transactional
	public void update(StockHistoryDto dto) {
		jdbcClient.sql(update)
			.param("productId", dto.productId())
			.param("version", dto.version())
			.param("purchasePrice", dto.purchasePrice())
			.param("salePrice", dto.salePrice())
			.param("amount", dto.amount())
			.update();
	}

}
