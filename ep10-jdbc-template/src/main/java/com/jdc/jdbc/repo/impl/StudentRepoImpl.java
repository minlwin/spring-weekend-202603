package com.jdc.jdbc.repo.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jdc.jdbc.repo.StudentRepo;
import com.jdc.jdbc.repo.output.StudentDetails;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class StudentRepoImpl implements StudentRepo {
	
	private final JdbcTemplate template;
	
	@Value("${app.sql.student.find-by-id}")
	private String findByIdSql;

	@Override
	public Optional<StudentDetails> findById(int id) {
		var result = template.query(findByIdSql, new DataClassRowMapper<>(StudentDetails.class), id);
		return result.stream().findAny();
	}

}
