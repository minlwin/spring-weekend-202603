package com.jdc.demo.service.jpql;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.demo.domain.input.StudentSearch;
import com.jdc.demo.domain.output.StudentForRegistration;
import com.jdc.demo.domain.output.StudentListItem;
import com.jdc.demo.service.base.AbstractStudentService;

@Service
@Profile("jpql")
public class StudentServiceJpql extends AbstractStudentService{

	@Override
	@Transactional(readOnly = true)
	public List<StudentListItem> search(StudentSearch form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentForRegistration find(String name, String phone, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer findId(String studentName, String studentPhone, String studentEmail) {
		var jpql = "select s.id from Student s where s.name = :name and s.phone = :phone and s.email = :email";
		return entityManager.createQuery(jpql, Integer.class)
			.setParameter("name", studentName)
			.setParameter("phone", studentPhone)
			.setParameter("email", studentEmail)
			.getResultList()
			.stream().findAny().orElse(null);
	
	}

}
