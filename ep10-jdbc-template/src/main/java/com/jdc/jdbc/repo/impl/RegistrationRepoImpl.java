package com.jdc.jdbc.repo.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jdc.jdbc.repo.ClassRepo;
import com.jdc.jdbc.repo.RegistrationRepo;
import com.jdc.jdbc.repo.StudentRepo;
import com.jdc.jdbc.repo.output.RegistrationItem;
import com.jdc.jdbc.utils.AppBusinessException;

@Repository
public class RegistrationRepoImpl implements RegistrationRepo{
	
	@Autowired
	private JdbcTemplate template;
	
	@Value("${app.sql.registrations.insert}")
	private String insertSql;
	
	@Autowired
	private ClassRepo classRepo;
	@Autowired
	private StudentRepo studentRepo;

	@Override
	public String create(int classId, int studentId) {
		
		if(classRepo.findById(classId).isEmpty()) {
			throw new AppBusinessException("There is no class with id %d.".formatted(classId));
		}
		
		if(studentRepo.findById(studentId).isEmpty()) {
			throw new AppBusinessException("There is no student with id %d.".formatted(studentId));
		}
		
		var code = "%03d%06d".formatted(classId, studentId);
		
		if(findById(code).isPresent()) {
			throw new AppBusinessException("Registration with id %s is already created.".formatted(code));
		}
		
		template.update(insertSql, classId, studentId);
		
		return code;
	}

	@Override
	public Optional<RegistrationItem> findById(String code) {
		
		
		
		return Optional.empty();
	}

}
