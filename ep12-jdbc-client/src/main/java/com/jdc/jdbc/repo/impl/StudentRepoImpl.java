package com.jdc.jdbc.repo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.simple.JdbcClient;
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
	
	private final StudentSqlProperties props;
	private final JdbcClient client;
	@Value("${app.sql.registrations.count-by-student}")
	private String countRegistrationSql;
	
	@Override
	public List<StudentItem> search(StudentSearch form) {
		var sql = new StringBuffer(props.getSearch());
		var params = new HashMap<String, Object>();
		
		if(StringUtils.hasLength(form.keyword())) {
			sql.append(" and lower(name) like :keyword");
			params.put("keyword", form.keyword().toLowerCase().concat("%"));
		}

		return client.sql(sql.toString())
				.params(params)
				.query(StudentItem.class)
				.list();
	}

	@Override
	public Optional<StudentDetails> findById(int id) {
		return client.sql(props.getFindById())
				.param("id", id)
				.query(StudentDetails.class)
				.optional();
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
		client.sql(props.getInsert())
			.paramSource(form)
			.update(keyHolder);
		
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
		
		var setClauseData = new ArrayList<String>();
		var params = new HashMap<String, Object>();
		
		params.put("id", id);
		
		if(StringUtils.hasLength(form.name())) {
			// name = ?
			setClauseData.add("name = :name");
			params.put("name", form.name());
		}

		if(StringUtils.hasLength(form.phone())) {
			// phone = ?
			setClauseData.add("phone = :phone");
			params.put("phone", form.phone());
		}

		if(StringUtils.hasLength(form.email())) {
			// email = ?
			setClauseData.add("email = :email");
			params.put("email", form.email());
		}
		
		if(setClauseData.isEmpty()) {
			throw new AppBusinessException("There is no fields for update student.");
		}
		
		var setClause = setClauseData.stream().collect(Collectors.joining(", "));
		var sql = props.getUpdate().formatted(setClause);

		return client.sql(sql)
				.params(params).update();
	}

	@Override
	public Integer delete(int id) {
		var student = findById(id);
		
		if(student.isPresent() && countInRegistration(id) > 0) {
			throw new AppBusinessException("%s is regisered in a class.".formatted(student.get().name()));
		}

		return client.sql(props.getDelete())
				.param("id", id)
				.update();
	}
	
	private long countInRegistration(int id) {
		return client.sql(countRegistrationSql)
				.param("studentId", id)
				.query(Long.class)
				.single();
	}

	private long countByEmail(String email) {
		return countByEmail(email, null);
	}

	private long countByEmail(String email, Integer id) {
		
		var sql = new StringBuffer(props.getCountByEmail());
		var params = new HashMap<String, Object>();
		params.put("email", email);
		
		if(null != id) {
			sql.append(" and id <> :id");
			params.put("id", id);
		}
		
		return client.sql(sql.toString())
				.params(params)
				.query(Long.class)
				.single();
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
