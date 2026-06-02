package com.jdc.demo.service.criteria;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.demo.domain.embeddables.Parent_;
import com.jdc.demo.domain.entity.Registration_;
import com.jdc.demo.domain.entity.Student;
import com.jdc.demo.domain.entity.Student_;
import com.jdc.demo.domain.input.StudentSearch;
import com.jdc.demo.domain.output.StudentListItem;
import com.jdc.demo.service.base.AbstractStudentService;

import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;

@Service
@Profile("criteria")
public class StudentServiceCriteria extends AbstractStudentService {

	@Override
	@Transactional(readOnly = true)
	public List<StudentListItem> search(StudentSearch form) {
		
		var cb = entityManager.getCriteriaBuilder();
		var cq = cb.createQuery(StudentListItem.class);
		
		var student = cq.from(Student.class);
		var registration = student.join(Student_.registrations, JoinType.LEFT);
		var father = student.get(Student_.father);
		var mother = student.get(Student_.mother);
		
		cq.select(cb.construct(StudentListItem.class, 
			student.get(Student_.id),
			student.get(Student_.name),
			student.get(Student_.phone),
			student.get(Student_.email),
			student.get(Student_.createdAt),
			cb.count(registration.get(Registration_.id)),
			cb.selectCase()
				.when(cb.isNotNull(father), father.get(Parent_.name))
				.when(cb.isNotNull(mother), mother.get(Parent_.name))
				.otherwise(""),
			cb.selectCase()
				.when(cb.isNotNull(father), father.get(Parent_.phone))
				.when(cb.isNotNull(mother), mother.get(Parent_.phone))
				.otherwise("")
		));
		
		var predicates = new ArrayList<Predicate>();
		
		if(null != form.getFrom()) {
			predicates.add(cb.greaterThanOrEqualTo(student.get(Student_.createdAt), form.getFrom().atStartOfDay()));
		}
		
		if(null != form.getTo()) {
			predicates.add(cb.lessThan(student.get(Student_.createdAt), form.getTo().plusDays(1).atStartOfDay()));
		}

		if(StringUtils.hasLength(form.getKeyword())) {
			var param = form.getKeyword().toLowerCase().concat("%");
			predicates.add(cb.or(
				cb.like(cb.lower(student.get(Student_.name)), param),
				cb.like(cb.lower(student.get(Student_.phone)), param),
				cb.like(cb.lower(student.get(Student_.email)), param)
			));
		}
		
		cq.where(predicates);	
		cq.groupBy(student.get(Student_.id));
		
		cq.orderBy(cb.desc(student.get(Student_.createdAt)));
		
		return entityManager.createQuery(cq).getResultList();
	}

}
