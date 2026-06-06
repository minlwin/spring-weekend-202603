package com.jdc.demo.service.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.demo.domain.entity.Student;
import com.jdc.demo.domain.output.StudentDetails;
import com.jdc.demo.service.StudentService;

import jakarta.persistence.EntityManager;

public abstract class AbstractStudentService implements StudentService{
	
	@Autowired
	protected EntityManager entityManager;

	@Override
	@Transactional(readOnly = true)
	public StudentDetails findById(int id) {
		var entity = entityManager.find(Student.class, id);
		return StudentDetails.from(entity);
	}

}
