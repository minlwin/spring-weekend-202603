package com.jdc.jdbc.repo.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.SimplePropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.jdc.jdbc.repo.ClassRepo;
import com.jdc.jdbc.repo.input.ClassForm;
import com.jdc.jdbc.repo.input.ClassSearch;
import com.jdc.jdbc.repo.output.ClassDetails;
import com.jdc.jdbc.repo.output.ClassItem;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ClassRepoImpl implements ClassRepo {
	
	private final JdbcTemplate template;
	
	private SimpleJdbcInsert insert;
	
	@Value("${app.sql.class.find-by-id}")
	private String findByIdSql;
	
	@PostConstruct
	public void init() {
		insert = new SimpleJdbcInsert(template);
		insert.setTableName("classes");
		insert.setGeneratedKeyName("id");
		insert.setColumnNames(List.of("course_id", "start_date", "months"));
	}

	@Override
	public Optional<ClassDetails> findById(int id) {
		var result = template.query(
				findByIdSql, 
				new DataClassRowMapper<>(ClassDetails.class), 
				id);
		
		return result.stream().findAny();
	}

	@Override
	public Integer create(ClassForm form) {
		// TODO 
		return insert.executeAndReturnKey(new SimplePropertySqlParameterSource(form))
			.intValue();
	}

	@Override
	public List<ClassItem> search(ClassSearch form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(int id, ClassForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
