package com.jdc.jdbc.repo.impl;

import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.jdc.jdbc.repo.StudentRepo;
import com.jdc.jdbc.repo.input.StudentForm;
import com.jdc.jdbc.repo.input.StudentSearch;
import com.jdc.jdbc.repo.output.ClassItem;
import com.jdc.jdbc.repo.output.StudentDetails;
import com.jdc.jdbc.utils.AppBusinessException;
import com.jdc.jdbc.utils.props.StudentSqlProperties;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class StudentRepoImpl implements StudentRepo {
	
	private final StudentSqlProperties sqls;
	private final JdbcTemplate template;
	
	@Override
	public Optional<StudentDetails> findById(int id) {
		var result = template.query(sqls.getFindById(), new DataClassRowMapper<>(StudentDetails.class), id);
		return result.stream().findAny();
	}

	@Override
	public List<ClassItem> search(StudentSearch form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer create(StudentForm form) {
		
		if(!isValidPhone(form.phone())) {
			throw new AppBusinessException("Invalid phone number.");
		}
		
		if(!isValidEmail(form.email())) {
			throw new AppBusinessException("Invalid email address.");
		}
		
		if(countByEmail(form.email()) > 0) {
			throw new AppBusinessException("Email is already used.");
		}
		
		var keyHolder = new GeneratedKeyHolder();
		
		template.update(conn -> {
			var stmt = conn.prepareStatement(sqls.getInsert(), Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, form.name());
			stmt.setString(2, form.phone());
			stmt.setString(3, form.email());
			return stmt;
		}, keyHolder);
		
		return keyHolder.getKey().intValue();
	}

	@Override
	public Integer update(int id, StudentForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	private long countByEmail(String email) {
		return template.queryForObject(sqls.getCountByEmail(), Long.class, email);
	}

	private boolean isValidPhone(String phone) {
		var pattern = "^(\\+959|09)[0-9\\- ]+$";
		return phone.matches(pattern);
	}

	private boolean isValidEmail(String email) {
		
		var array = email.split("@");
		if(array.length >= 2) {
			var domain = array[array.length - 1];
			if(domain.contains(".")) {
				return true;
			}
		}
		return false;
	}

}
