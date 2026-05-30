package com.jdc.demo.service.jpql;

import java.util.HashMap;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.demo.domain.input.CourseSearch;
import com.jdc.demo.domain.output.CourseListItem;
import com.jdc.demo.service.base.AbstractCourseService;

@Service
@Profile("jpql")
@Transactional(readOnly = true)
public class CourseServiceJpql extends AbstractCourseService {

	@Override
	public List<CourseListItem> search(CourseSearch form) {
		
		var qb = new StringBuffer("select new com.jdc.demo.domain.output.CourseListItem(c) from Course c where 1 = 1");
		var params = new HashMap<String, Object>();
		
		if(null != form.getLevel()) {
			qb.append(" and c.level = :level");
			params.put("level", form.getLevel());
		}
		
		if(StringUtils.hasLength(form.getKeyword())) {
			qb.append(" and lower(c.name) like :keyword");
			params.put("keyword", form.getKeyword().toLowerCase().concat("%"));
		}

		// Create Query from JPQL
		var query = em.createQuery(qb.toString(), CourseListItem.class);
		
		for(var key : params.keySet()) {
			query.setParameter(key, params.get(key));
		}
		
		return query.getResultList();
	}

}
