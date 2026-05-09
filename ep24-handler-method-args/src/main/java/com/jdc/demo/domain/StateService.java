package com.jdc.demo.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
	
	@Value("${app.sql.state.search}")
	private String search;

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

	public List<StateDetails> search(String keyword, Integer from, Integer to) {
		
		// select * from state
		var sqlBuilder = new StringBuffer(search);
		var params = new HashMap<String, Object>();
		
		if(StringUtils.hasLength(keyword)) {
			sqlBuilder.append(" and lower(name) like :keyword and lower(capital) like :keyword");
			params.put("keyword", "%s%%".formatted(keyword.toLowerCase()));
		}
		
		if(null != from) {
			sqlBuilder.append(" and population >= :from");
			params.put("from", from);
		}
		
		if(null != to) {
			sqlBuilder.append(" and population <= :to");
			params.put("to", to);
		}
		
		return client.sql(sqlBuilder.toString())
				.params(params)
				.query(StateDetails.class)
				.list();
	}
}
