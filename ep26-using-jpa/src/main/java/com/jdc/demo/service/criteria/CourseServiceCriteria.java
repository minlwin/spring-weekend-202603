package com.jdc.demo.service.criteria;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jdc.demo.domain.entity.Course;
import com.jdc.demo.domain.entity.Course_;
import com.jdc.demo.domain.input.CourseSearch;
import com.jdc.demo.domain.output.CourseListItem;
import com.jdc.demo.service.base.AbstractCourseService;

import jakarta.persistence.criteria.Predicate;

@Service
@Profile("criteria")
public class CourseServiceCriteria extends AbstractCourseService{

	@Override
	public List<CourseListItem> search(CourseSearch form) {
		
		var cb = em.getCriteriaBuilder();
		var cq = cb.createQuery(CourseListItem.class);
		
		// from Course root
		var root = cq.from(Course.class);
		
		// select root
		cq.select(cb.construct(CourseListItem.class, root));
		
		var params = new ArrayList<Predicate>();
		
		if(null != form.getLevel()) {
			// root.level = :level
			params.add(cb.equal(root.get(Course_.level), form.getLevel()));
		}
		
		if(StringUtils.hasLength(form.getKeyword())) {
			// lower(root.name) like :level
			params.add(cb.like(cb.lower(root.get(Course_.name)), form.getKeyword().toLowerCase().concat("%")));
		}
		
		cq.where(params);
		
		var query = em.createQuery(cq);
		
		return query.getResultList();
	}


}
