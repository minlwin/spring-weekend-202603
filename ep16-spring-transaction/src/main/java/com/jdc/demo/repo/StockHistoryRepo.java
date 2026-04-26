package com.jdc.demo.repo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import com.jdc.demo.domains.output.StockHistoryDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class StockHistoryRepo {

	private final JdbcClient jdbcClient;
	
	@Value("${app.sql.stock-history.create}")
	private String create;
	
	public void create(StockHistoryDto dto) {
		jdbcClient.sql(create)
			.paramSource(dto)
			.update();
	}

}
