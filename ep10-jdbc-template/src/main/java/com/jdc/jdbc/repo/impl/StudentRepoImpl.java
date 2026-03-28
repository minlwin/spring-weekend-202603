package com.jdc.jdbc.repo.impl;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.jdc.jdbc.repo.StudentRepo;
import com.jdc.jdbc.repo.input.StudentForm;
import com.jdc.jdbc.repo.input.StudentSearch;
import com.jdc.jdbc.repo.output.StudentDetails;
import com.jdc.jdbc.repo.output.StudentItem;
import com.jdc.jdbc.utils.AppBusinessException;
import com.jdc.jdbc.utils.props.StudentSqlProperties;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class StudentRepoImpl implements StudentRepo {
	
	private final StudentSqlProperties sqls;
	private final JdbcTemplate template;
	
	@Value("${app.sql.registrations.count-by-student}")
	private String countRegistrationSql;
	
	@Override
	public Optional<StudentDetails> findById(int id) {
		var result = template.query(sqls.getFindById(), new DataClassRowMapper<>(StudentDetails.class), id);
		return result.stream().findAny();
	}

	@Override
	public List<StudentItem> search(StudentSearch form) {
		
		var sql = new StringBuffer(sqls.getSearch());
		var params = new ArrayList<Object>();
		
		if(StringUtils.hasLength(form.keyword())) {
			sql.append(" where lower(name) like ?");
			params.add(form.keyword().toLowerCase().concat("%"));
		}
		
		return template.query(sql.toString(), 
				new DataClassRowMapper<>(StudentItem.class), 
				params.toArray());
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

		if(StringUtils.hasLength(form.phone()) && !isValidPhone(form.phone())) {
			throw new AppBusinessException("Invalid phone number.");
		}
		
		if(StringUtils.hasLength(form.email()) && !isValidEmail(form.email())) {
			throw new AppBusinessException("Invalid email address.");
		}
		
		if(StringUtils.hasLength(form.email()) && countByEmail(form.email(), id) > 0) {
			throw new AppBusinessException("Email is already used.");
		}
		
		var sql = new ArrayList<String>();
		var params = new ArrayList<Object>();
		
		if(StringUtils.hasLength(form.name())) {
			// name = ?
			sql.add("name = ?");
			params.add(form.name());
		}

		if(StringUtils.hasLength(form.phone())) {
			// phone = ?
			sql.add("phone = ?");
			params.add(form.phone());
		}

		if(StringUtils.hasLength(form.email())) {
			// email = ?
			sql.add("email = ?");
			params.add(form.email());
		}
		
		if(sql.isEmpty()) {
			throw new AppBusinessException("There is no fields for update student.");
		}
		
		var setCaluse = sql.stream().collect(Collectors.joining(", "));
		params.add(id);

		return template.update(sqls.getUpdate().formatted(setCaluse), params.toArray());
	}

	@Override
	public Integer delete(int id) {
		
		var student = findById(id);
		
		if(student.isPresent() && countInRegistration(id) > 0) {
			throw new AppBusinessException("%s is regisered in a class.".formatted(student.get().name()));
		}
		
		return template.update(sqls.getDelete(), id);
	}

	private long countInRegistration(int id) {
		return template.queryForObject(countRegistrationSql, Long.class, id);
	}

	private long countByEmail(String email) {
		return countByEmail(email, null);
	}

	private long countByEmail(String email, Integer id) {
		
		var sql = new StringBuffer(sqls.getCountByEmail());
		var params = new ArrayList<Object>();
		params.add(email);
		
		if(null != id) {
			sql.append(" and id <> ?");
			params.add(id);
		}
		
		return template.queryForObject(sql.toString(), Long.class, params.toArray());
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
