package com.jdc.demo.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.demo.domain.input.CourseForm;
import com.jdc.demo.domain.input.CourseSearch;
import com.jdc.demo.domain.output.CourseDetails;
import com.jdc.demo.domain.output.CourseListItem;

@Service
@Transactional(readOnly = true)
public class CourseService {
	
	@Autowired
	private JdbcClient client;
	
	@Value("${app.sql.course.insert}")
	private String create;
	@Value("${app.sql.course.search}")
	private String search;
	@Value("${app.sql.course.find-by-id}")
	private String findById;
	@Value("${app.sql.course.update}")
	private String update;

	@Transactional
	public int create(CourseForm form) {
		
		var keyHolder = new GeneratedKeyHolder();
		
		client.sql(create)
			.paramSource(form)
			.update(keyHolder);
		
		return keyHolder.getKey().intValue();
	}

	public List<CourseListItem> search(CourseSearch form) {
		
		var params = new HashMap<String, Object>();
		var sqlBuilder = new StringBuffer(search);
		
		if(null != form.getLevel()) {
			sqlBuilder.append(" and level = :level");
			params.put("level", form.getLevel());
		}
		
		if(StringUtils.hasLength(form.getKeyword())) {
			sqlBuilder.append(" and lower(name) like :keyword");
			params.put("keyword", form.getKeyword().toLowerCase().concat("%"));
		}
		
		return client.sql(sqlBuilder.toString())
				.params(params)
				.query(CourseListItem.class)
				.list();
	}

	public CourseDetails findById(int id) {
		return client.sql(findById)
				.param("id", id)
				.query(CourseDetails.class)
				.single();
	}

	@Transactional
	public void update(Integer id, CourseForm form) {
		client.sql(update)
			.param("id", id)
			.param("name", form.getName())
			.param("level", form.getLevel())
			.param("hours", form.getHours())
			.param("description", form.getDescription())
			.update();
	}

}
