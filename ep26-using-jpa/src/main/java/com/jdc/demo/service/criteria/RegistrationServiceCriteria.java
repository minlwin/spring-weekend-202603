package com.jdc.demo.service.criteria;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.demo.domain.entity.Classes_;
import com.jdc.demo.domain.entity.Course_;
import com.jdc.demo.domain.entity.Registration;
import com.jdc.demo.domain.entity.Registration_;
import com.jdc.demo.domain.entity.Student_;
import com.jdc.demo.domain.input.RegistrationSearch;
import com.jdc.demo.domain.output.RegistrationListItem;
import com.jdc.demo.service.base.AbstractRegistrationService;

import jakarta.persistence.criteria.Predicate;

@Service
@Profile("criteria")
public class RegistrationServiceCriteria extends AbstractRegistrationService{

	@Override
	@Transactional(readOnly = true)
	public List<RegistrationListItem> search(RegistrationSearch form) {
		var cb = entityManager.getCriteriaBuilder();
		var cq = cb.createQuery(RegistrationListItem.class);
		
		var registration = cq.from(Registration.class);
		var student = registration.join(Registration_.student);
		var intake = registration.join(Registration_.intake);
		var course = intake.join(Classes_.course);
		
		cq.select(cb.construct(RegistrationListItem.class, 
			registration.get(Registration_.id),
			course.get(Course_.id),
			course.get(Course_.name),
			intake.get(Classes_.startDate),
			student.get(Student_.id),
			student.get(Student_.name),
			student.get(Student_.phone),
			student.get(Student_.email),
			student.get(Student_.createdAt)
		));
		
		var predicates = new ArrayList<Predicate>();
		
		if(null != form.getFrom()) {
			predicates.add(cb.greaterThanOrEqualTo(registration.get(Registration_.registDate), form.getFrom().atStartOfDay()));
		}
		
		if(null != form.getTo()) {
			predicates.add(cb.lessThan(registration.get(Registration_.registDate), form.getTo().plusDays(1).atStartOfDay()));
		}

		if(StringUtils.hasLength(form.getKeyword())) {
			var param = form.getKeyword().toLowerCase().concat("%");
			predicates.add(cb.or(
				cb.like(cb.lower(course.get(Course_.name)), param),
				cb.like(cb.lower(student.get(Student_.name)), param),
				cb.like(cb.lower(student.get(Student_.phone)), param),
				cb.like(cb.lower(student.get(Student_.email)), param)
			));
		}
		
		cq.where(predicates);
		cq.orderBy(cb.desc(registration.get(Registration_.registDate)));

		return entityManager.createQuery(cq).getResultList();
	}

}
