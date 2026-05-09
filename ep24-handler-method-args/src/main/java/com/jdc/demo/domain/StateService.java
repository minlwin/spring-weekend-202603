package com.jdc.demo.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.demo.domain.dto.StateDetails;
import com.jdc.demo.domain.dto.StateListItem;

@Service
@Transactional(readOnly = true)
public class StateService {
	
	@Autowired
	private JdbcClient client;
	
	@Value("${app.sql.state.find-all}")
	private String findAll;
	@Value("${app.sql.state.find-by-id}")
	private String findById;

	public List<StateListItem> findAll() {
		return client.sql(findAll)
				.query(StateListItem.class)
				.list();
	}
	
	public Optional<StateDetails> findById(int id) {
		return client.sql(findById)
				.param("id", id)
				.query(StateDetails.class)
				.optional();
	}
}
