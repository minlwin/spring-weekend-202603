package com.jdc.jdbc.repo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SimplePropertySqlParameterSource;
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
	private final NamedParameterJdbcTemplate template;
	@Value("${app.sql.registrations.count-by-student}")
	private String countRegistrationSql;
	
	@Override
	public List<StudentItem> search(StudentSearch form) {
		var sql = new StringBuffer(sqls.getSearch());
		var params = new HashMap<String, Object>();
		
		if(StringUtils.hasLength(form.keyword())) {
			sql.append(" and lower(name) like :keyword");
			params.put("keyword", form.keyword().toLowerCase().concat("%"));
		}
		
		return template.query(sql.toString(), params, new DataClassRowMapper<>(StudentItem.class));
	}

	@Override
	public Optional<StudentDetails> findById(int id) {
		return template.query(sqls.getFindById(), 
				Map.of("id", id), 
				new DataClassRowMapper<>(StudentDetails.class))
			.stream().findAny();
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
		
		var params = new SimplePropertySqlParameterSource(form);
		var keyHolder = new GeneratedKeyHolder();
		
		template.update(sqls.getInsert(), params, keyHolder);

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
		var params = new HashMap<String, Object>();
		
		params.put("id", id);
		
		if(StringUtils.hasLength(form.name())) {
			// name = ?
			sql.add("name = :name");
			params.put("name", form.name());
		}

		if(StringUtils.hasLength(form.phone())) {
			// phone = ?
			sql.add("phone = :phone");
			params.put("phone", form.phone());
		}

		if(StringUtils.hasLength(form.email())) {
			// email = ?
			sql.add("email = :email");
			params.put("email", form.email());
		}
		
		if(sql.isEmpty()) {
			throw new AppBusinessException("There is no fields for update student.");
		}
		
		var setClause = sql.stream().collect(Collectors.joining(", "));
		return template.update(sqls.getUpdate().formatted(setClause), params);
	}

	@Override
	public Integer delete(int id) {
		var student = findById(id);
		
		if(student.isPresent() && countInRegistration(id) > 0) {
			throw new AppBusinessException("%s is regisered in a class.".formatted(student.get().name()));
		}
		
		return template.update(sqls.getDelete(), Map.of("id", id));
	}

	private long countInRegistration(int id) {
		return template.queryForObject(countRegistrationSql, Map.of("studentId", id), Long.class);
	}

	private long countByEmail(String email) {
		return countByEmail(email, null);
	}

	private long countByEmail(String email, Integer id) {
		
		var sql = new StringBuffer(sqls.getCountByEmail());
		var params = new HashMap<String, Object>();
		params.put("email", email);
		
		if(null != id) {
			sql.append(" and id <> :id");
			params.put("id", id);
		}
		
		return template.queryForObject(sql.toString(), params,Long.class);
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
