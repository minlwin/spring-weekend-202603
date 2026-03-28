package com.jdc.jdbc.repo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.SimplePropertySqlParameterSource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.jdc.jdbc.repo.CourseRepo;
import com.jdc.jdbc.repo.input.CourseForm;
import com.jdc.jdbc.repo.input.CourseSearch;
import com.jdc.jdbc.repo.output.CourseDetails;
import com.jdc.jdbc.repo.output.CourseItem;
import com.jdc.jdbc.utils.AppBusinessException;
import com.jdc.jdbc.utils.props.CourseSqlProperties;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CourseRepoImpl implements CourseRepo {
	
	@Value("${app.sql.class.count-by-course}")
	private String countClassSql;
	
	private final CourseSqlProperties props;
	
	private final JdbcClient client;
	private final DataSource dataSource;
	
	private SimpleJdbcInsert insert;
	
	@PostConstruct
	private void init() {
		insert = new SimpleJdbcInsert(dataSource)
			.withTableName("courses")
			.usingGeneratedKeyColumns("id");
	}
	
	@Override
	public List<CourseItem> search(CourseSearch form) {
		var sb = new StringBuffer(props.getSearch());
		var params = new ArrayList<Object>();
		
		if(StringUtils.hasLength(form.keyword())) {
			sb.append(" and name like ?");
			params.add(form.keyword().toLowerCase().concat("%"));
		}
		
		if(null != form.feesFrom()) {
			sb.append(" and fees >= ?");
			params.add(form.feesFrom());
		}
		
		if(null != form.feesTo()) {
			sb.append(" and fees <= ?");
			params.add(form.feesTo());
		}
		
		return client.sql(sb.toString()).params(params)
				.query(new DataClassRowMapper<>(CourseItem.class))
				.list();
	}

	@Override
	public Optional<CourseDetails> findById(int id) {
		return client.sql(props.getFindById()).param(id)
				.query(new DataClassRowMapper<>(CourseDetails.class))
				.optional();
	}

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
		
		var params = new SimplePropertySqlParameterSource(form);
		return insert.executeAndReturnKeyHolder(params)
				.getKey().intValue();
	}

	@Override
	public int update(int id, CourseForm form) {
		
		if(null != form.hours() && form.hours() <= 0) {
			throw new AppBusinessException("Hours must be greater than Zero.");
		}
		
		if(null != form.fees() && form.fees() < 0) {
			throw new AppBusinessException("Fees must not be negative value.");
		}
		
		if(StringUtils.hasLength(form.name()) && findCountByName(form.name(), id) > 0) {
			throw new AppBusinessException("%s is already created.".formatted(form.name()));
		}
		
		var setSpec = new ArrayList<String>();
		var params = new ArrayList<Object>();
		
		if(StringUtils.hasLength(form.name())) {
			setSpec.add("name = ?");
			params.add(form.name());
		}
		
		if(null != form.hours()) {
			setSpec.add("hours = ?");
			params.add(form.hours());
		}
		
		if(null != form.fees()) {
			setSpec.add("fees = ?");
			params.add(form.fees());
		}
		
		if(StringUtils.hasLength(form.description())) {
			setSpec.add("description = ?");
			params.add(form.description());
		}
		
		if(params.isEmpty()) {
			throw new AppBusinessException("There is no fields for update course.");
		}
		
		var setClause = setSpec.stream().collect(Collectors.joining(", "));
		var sql = props.getUpdate().formatted(setClause);
		
		params.add(id);
		
		return client.sql(sql)
				.params(params).update();
	}

	@Override
	public int delete(int id) {
		if(countClass(id) > 0) {
			var course = findById(id).get();
			throw new AppBusinessException("%s is already used in class.".formatted(course.name()));
		}

		return client.sql(props.getDelete()).param(id).update();
	}
	
	private long findCountByName(String name) {
		return findCountByName(name, null);
	}
	
	private long countClass(int id) {
		return client.sql(countClassSql)
				.param(id)
				.query(Long.class)
				.single();
	}
	
	private long findCountByName(String name, Integer id) {
		
		var sb = new StringBuffer(props.getCountByName());
		var params = new ArrayList<Object>();
		params.add(name);
		
		if(null != id) {
			sb.append(" and id <> ?");
			params.add(id);
		}
		
		return client.sql(sb.toString())
				.params(params)
				.query(Long.class)
				.single();
	}
	

}
