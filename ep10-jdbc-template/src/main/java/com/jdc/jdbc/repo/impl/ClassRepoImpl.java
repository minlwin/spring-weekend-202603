package com.jdc.jdbc.repo.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jdc.jdbc.repo.ClassRepo;
import com.jdc.jdbc.repo.output.ClassDetails;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ClassRepoImpl implements ClassRepo {
	
	private final JdbcTemplate template;
	
	@Value("${app.sql.class.find-by-id}")
	private String findByIdSql;

	@Override
	public Optional<ClassDetails> findById(int id) {
		var result = template.query(
				findByIdSql, 
				new DataClassRowMapper<>(ClassDetails.class), 
				id);
		
		return result.stream().findAny();
	}

}
