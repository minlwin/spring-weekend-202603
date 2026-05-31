package com.jdc.demo.service.criteria;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.jdc.demo.domain.entity.Classes;
import com.jdc.demo.domain.entity.Classes_;
import com.jdc.demo.domain.entity.Course_;
import com.jdc.demo.domain.entity.Registration_;
import com.jdc.demo.domain.input.ClassesSearch;
import com.jdc.demo.domain.output.ClassesListItem;
import com.jdc.demo.service.base.AbstractClassesService;

import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;

@Service
@Profile("criteria")
public class ClassesServiceCriteria extends AbstractClassesService {

	@Override
	public List<ClassesListItem> search(ClassesSearch form) {
		
		var cb = entityManager.getCriteriaBuilder();
		var cq = cb.createQuery(ClassesListItem.class);
		
		var root = cq.from(Classes.class);
		var course = root.join(Classes_.course);
		var registration = root.join(Classes_.registrations, JoinType.LEFT);
		
		cq.select(cb.construct(ClassesListItem.class, 
			root.get(Classes_.id),
			course.get(Course_.id),
			course.get(Course_.name),
			course.get(Course_.level),
			root.get(Classes_.startDate),
			root.get(Classes_.months),
			root.get(Classes_.status),
			root.get(Classes_.fees),
			root.get(Classes_.availableSeats),
			cb.count(registration.get(Registration_.id))
		));
		
		cq.groupBy(
			root.get(Classes_.id),
			course.get(Course_.id),
			course.get(Course_.name),
			course.get(Course_.level),
			root.get(Classes_.startDate),
			root.get(Classes_.months),
			root.get(Classes_.status),
			root.get(Classes_.fees),
			root.get(Classes_.availableSeats)
		);
		
		cq.orderBy(cb.desc(root.get(Classes_.startDate)));
		
		if(null != form) {
			var predicates = new ArrayList<Predicate>();
			
			if(null != form.getCourseId()) {
				predicates.add(cb.equal(course.get(Course_.id), form.getCourseId()));
			}
			
			if(null != form.getStatus()) {
				predicates.add(cb.equal(root.get(Classes_.status), form.getStatus()));
			}
			
			if(null != form.getStartFrom()) {
				predicates.add(cb.greaterThanOrEqualTo(root.get(Classes_.startDate), form.getStartFrom()));
			}
			
			cq.where(predicates);
		}
		
		return entityManager.createQuery(cq).getResultList();
	}

}
