package com.jdc.jdbc.repo.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.SimplePropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.jdc.jdbc.repo.ClassRepo;
import com.jdc.jdbc.repo.CourseRepo;
import com.jdc.jdbc.repo.input.ClassForm;
import com.jdc.jdbc.repo.input.ClassSearch;
import com.jdc.jdbc.repo.output.ClassDetails;
import com.jdc.jdbc.repo.output.ClassItem;
import com.jdc.jdbc.utils.AppBusinessException;
import com.jdc.jdbc.utils.props.ClassSqlProperties;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ClassRepoImpl implements ClassRepo {
	
	private final ClassSqlProperties sqls;

	private final JdbcTemplate template;
	private SimpleJdbcInsert insert;
	
	private final CourseRepo courseRepo;
	
	@Value("${app.sql.registrations.count-by-class}")
	private String registrationByClass;

	
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
				sqls.getFindById(), 
				new DataClassRowMapper<>(ClassDetails.class), 
				id);
		
		return result.stream().findAny();
	}

	@Override
	public Integer create(ClassForm form) {
		
		if(courseRepo.findById(form.courseId()).isEmpty()) {
			throw new AppBusinessException("There is no course with id %s.".formatted(form.courseId()));
		}
		
		if(null == form.startDate() || LocalDate.now().compareTo(form.startDate()) >= 0) {
			throw new AppBusinessException("Start date must be future date.");
		}
		
		if(LocalDate.now().plusMonths(1).compareTo(form.startDate()) < 0) {
			throw new AppBusinessException("Start date must be within 1 month.");
		}
		
		if(form.months() < 1 || form.months() > 12) {
			throw new AppBusinessException("Months must be between 1 and 12.");
		}
		
		return insert.executeAndReturnKey(new SimplePropertySqlParameterSource(form))
			.intValue();
	}

	@Override
	public List<ClassItem> search(ClassSearch form) {
		
		var sql = new StringBuffer(sqls.getSearch());
		var params = new ArrayList<Object>();
		
		if(StringUtils.hasLength(form.keyword())) {
			sql.append(" and lower(cs.name) like ?");
			params.add(form.keyword().toLowerCase().concat("%"));
		}
		
		if(null != form.dateFrom()) {
			sql.append(" and cl.start_date >= ?");
			params.add(Date.valueOf(form.dateFrom()));
		}
		
		if(null != form.dateTo()) {
			sql.append(" and cl.start_date <= ?");
			params.add(Date.valueOf(form.dateTo()));
		}
		
		return template.query(sql.toString(), new DataClassRowMapper<>(ClassItem.class), params.toArray());
	}

	@Override
	public Integer update(int id, ClassForm form) {
		
		var setClauses = new ArrayList<String>();
		var params = new ArrayList<Object>();
		
		if(null != form.courseId()) {
			if(courseRepo.findById(form.courseId()).isEmpty()) {
				throw new AppBusinessException("There is no course with id %s.".formatted(form.courseId()));
			}
			setClauses.add("course_id = ?");
			params.add(form.courseId());
		}
		
		if(null != form.startDate()) {
			
			if(LocalDate.now().compareTo(form.startDate()) >= 0) {
				throw new AppBusinessException("Start date must be future date.");
			}
			
			if(LocalDate.now().plusMonths(1).compareTo(form.startDate()) < 0) {
				throw new AppBusinessException("Start date must be within 1 month.");
			}

			setClauses.add("start_date = ?");
			params.add(form.startDate());
		}
		
		if(null != form.months()) {
			if(form.months() < 1 || form.months() > 12) {
				throw new AppBusinessException("Months must be between 1 and 12.");
			}
			setClauses.add("months = ?");
			params.add(form.months());
		}
		
		if(setClauses.isEmpty()) {
			throw new AppBusinessException("There is no fields to update.");
		}
		
		params.add(id);
		var setClause = setClauses.stream().collect(Collectors.joining(", "));
		var sql = sqls.getUpdate().formatted(setClause);
		
		return template.update(sql, params.toArray());
	}

	@Override
	public Integer delete(int id) {
		
		if(template.queryForObject(registrationByClass, Long.class, id) > 0) {
			throw new AppBusinessException("Registered class can't be deleted.");
		}
		
		return template.update(sqls.getDelete(), id);
	}

}
