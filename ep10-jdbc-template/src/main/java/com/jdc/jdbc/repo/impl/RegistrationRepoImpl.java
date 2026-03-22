package com.jdc.jdbc.repo.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.jdc.jdbc.repo.ClassRepo;
import com.jdc.jdbc.repo.RegistrationRepo;
import com.jdc.jdbc.repo.StudentRepo;
import com.jdc.jdbc.repo.input.RegistrationSearch;
import com.jdc.jdbc.repo.output.RegistrationItem;
import com.jdc.jdbc.utils.AppBusinessException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RegistrationRepoImpl implements RegistrationRepo{
	
	private final JdbcTemplate template;
	private final ClassRepo classRepo;
	private final StudentRepo studentRepo;
	
	@Value("${app.sql.registrations.insert}")
	private String insertSql;
	
	@Value("${app.sql.registrations.find-by-id}")
	private String findByIdSql;
	
	@Value("${app.sql.registrations.delete}")
	private String deleteSql;

	@Value("${app.sql.registrations.search}")
	private String searchSql;
	
	private RowMapper<RegistrationItem> itemMapper = new DataClassRowMapper<>(RegistrationItem.class);
	

	@Override
	public String create(int classId, int studentId) {
		
		if(classRepo.findById(classId).isEmpty()) {
			throw new AppBusinessException("There is no class with id %d.".formatted(classId));
		}
		
		if(studentRepo.findById(studentId).isEmpty()) {
			throw new AppBusinessException("There is no student with id %d.".formatted(studentId));
		}
		
		var code = "%03d%06d".formatted(classId, studentId);
		
		if(findById(code).isPresent()) {
			throw new AppBusinessException("Registration with id %s is already created.".formatted(code));
		}
		
		template.update(insertSql, classId, studentId);
		
		return code;
	}

	@Override
	public Optional<RegistrationItem> findById(String code) {
		
		var classId = Integer.parseInt(code.substring(0, 3));
		var studentId = Integer.parseInt(code.substring(3));

		var result = template.query(findByIdSql, itemMapper, classId, studentId);

		return result.stream().findAny();
	}

	@Override
	public List<RegistrationItem> search(RegistrationSearch form) {
		var sqlBuilder = new StringBuffer(searchSql);
		var params = new ArrayList<>();
		
		if(StringUtils.hasLength(form.keyword())) {
			sqlBuilder.append(" and (lower(cs.name) like ? or lower(st.name) like ?)");
			params.add(form.keyword().toLowerCase().concat("%"));
			params.add(form.keyword().toLowerCase().concat("%"));
		}
		
		if(null != form.from()) {
			sqlBuilder.append(" and rg.issue_at >= ?");
			params.add(Timestamp.valueOf(form.from().atStartOfDay()));
		}
		
		if(null != form.to()) {
			sqlBuilder.append(" and rg.issue_at < ?");
			params.add(Timestamp.valueOf(form.to().plusDays(1).atStartOfDay()));
		}
		
		return template.query(sqlBuilder.toString(), itemMapper, params.toArray());
	}

	@Override
	public int delete(String code) {
		var classId = Integer.parseInt(code.substring(0, 3));
		var studentId = Integer.parseInt(code.substring(3));
		return template.update(deleteSql, classId, studentId);
	}

}
