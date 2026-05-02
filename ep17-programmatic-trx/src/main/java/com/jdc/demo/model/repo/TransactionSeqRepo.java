package com.jdc.demo.model.repo;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.demo.model.output.TransactionSeq;

@Repository
public class TransactionSeqRepo {

	@Autowired
	private JdbcClient client;
	
	@Value("${app.sql.transaction-seq.find-by-id}")
	private String findById;
	@Value("${app.sql.transaction-seq.create}")
	private String create;
	@Value("${app.sql.transaction-seq.update}")
	private String update;
	
	@Transactional(readOnly = true)
	public Optional<TransactionSeq> findById(LocalDate date) {
		return client.sql(findById)
				.param("id", date)
				.query(TransactionSeq.class)
				.optional();
	}

	@Transactional
	public int create(TransactionSeq newSeq) {
		return client.sql(create)
				.paramSource(newSeq)
				.update();
	}

	@Transactional
	public int update(TransactionSeq nextSeq) {
		return client.sql(update)
				.paramSource(nextSeq)
				.update();
	}
}
