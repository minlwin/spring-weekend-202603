package com.jdc.jdbc.repo.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.SimplePropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

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
		
		if(null == form.startDate() || LocalDate.now().isAfter(form.startDate())) {
			throw new AppBusinessException("Start date must be future date.");
		}
		
		if(LocalDate.now().plusMonths(1).isBefore(form.startDate())) {
			throw new AppBusinessException("Start date must be within 1 month.");
		}
		
		if(form.months() < 1 || form.months() > 12) {
			throw new AppBusinessException("Start date must be within 1 month.");
		}
		
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
