package com.jdc.jdbc.repo.impl;

import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.jdc.jdbc.repo.CourseRepo;
import com.jdc.jdbc.repo.input.CourseForm;
import com.jdc.jdbc.repo.input.CourseSearch;
import com.jdc.jdbc.repo.output.CourseDetails;
import com.jdc.jdbc.repo.output.CourseItem;
import com.jdc.jdbc.utils.AppBusinessException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CourseRepoImpl implements CourseRepo {
	
	private final JdbcTemplate template;
	
	@Value("${app.sql.course.insert}")
	private String insertSql;
	
	@Value("${app.sql.course.count-by-name}")
	private String countByNameSql;

	@Override
	public Integer create(CourseForm form) {
		
		if(form.hours() <= 0) {
			throw new AppBusinessException("Hours must be greater than Zero.");
		}
		
		if(form.fees() < 0) {
			throw new AppBusinessException("Fees must not be negative value.");
		}
		
		if(findCountByName(form.name()) > 0) {
			throw new AppBusinessException("%s course is already created.".formatted(form.name()));
		}
		
		var keyHolder = new GeneratedKeyHolder();
		
		template.update(conn -> {
			var stmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, form.name());
			stmt.setInt(2, form.hours());
			stmt.setInt(3, form.fees());
			stmt.setString(4, form.description());
			return stmt;
		}, keyHolder);
		
		return keyHolder.getKey().intValue();
	}

	@Override
	public Optional<CourseDetails> findById(int id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<CourseItem> search(CourseSearch form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(int id, CourseForm form) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	private long findCountByName(String name) {
		return template.queryForObject(countByNameSql, Long.class, name);
	}
	
}
