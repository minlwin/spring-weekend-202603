package com.jdc.jdbc.repo.impl;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.jdc.jdbc.repo.CourseRepo;
import com.jdc.jdbc.repo.input.CourseForm;
import com.jdc.jdbc.repo.input.CourseSearch;
import com.jdc.jdbc.repo.output.CourseDetails;
import com.jdc.jdbc.repo.output.CourseItem;
import com.jdc.jdbc.utils.AppBusinessException;
import com.jdc.jdbc.utils.props.CourseSqlProperties;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CourseRepoImpl implements CourseRepo {
	
	private final JdbcTemplate template;
	
	@Autowired
	private CourseSqlProperties props;

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
			var stmt = conn.prepareStatement(props.getInsert(), Statement.RETURN_GENERATED_KEYS);
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
		return template.query(props.getFindById(), new DataClassRowMapper<>(CourseDetails.class), id)
				.stream().findAny();
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
		
		return template.query(sb.toString(), new DataClassRowMapper<>(CourseItem.class), params.toArray());
	}

	@Override
	public int update(int id, CourseForm form) {
		
		if(!StringUtils.hasLength(form.name())
				&& null == form.fees()
				&& null == form.hours()
				&& !StringUtils.hasLength(form.description())) {
			throw new AppBusinessException("There is no fields for update course.");
		}
		
		if(null != form.hours() && form.hours() <= 0) {
			throw new AppBusinessException("Hours must be greater than Zero.");
		}
		
		if(null != form.fees() && form.fees() < 0) {
			throw new AppBusinessException("Fees must not be negative value.");
		}
		
		if(StringUtils.hasLength(form.name()) && findCountByName(form.name(), id) > 0) {
			throw new AppBusinessException("%s course is already created.".formatted(form.name()));
		}

		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	private long findCountByName(String name) {
		return findCountByName(name, null);
	}
	
	private long findCountByName(String name, Integer id) {
		
		var sb = new StringBuffer(props.getCountByName());
		var params = new ArrayList<Object>();
		params.add(name);
		
		if(null != id) {
			sb.append(" and id <> ?");
			params.add(id);
		}
		
		return template.queryForObject(sb.toString(), Long.class, params.toArray());
	}
}
