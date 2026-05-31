package com.jdc.demo.service.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.demo.domain.entity.Classes;
import com.jdc.demo.domain.entity.Classes.Status;
import com.jdc.demo.domain.entity.Course;
import com.jdc.demo.domain.input.ClassesForm;
import com.jdc.demo.domain.output.ClassesDetails;
import com.jdc.demo.service.ClassesService;

import jakarta.persistence.EntityManager;

@Transactional(readOnly = true)
public abstract class AbstractClassesService implements ClassesService{
	
	@Autowired
	protected EntityManager entityManager;
	
	@Override
	public ClassesDetails findById(int id) {
		var entity = entityManager.find(Classes.class, id);
		return ClassesDetails.from(entity);
	}
	
	@Override
	@Transactional
	public Integer create(ClassesForm form) {
		var course = entityManager.getReference(Course.class, form.getCourseId());
		var entity = new Classes();
		entity.setCourse(course);
		entity.setAvailableSeats(form.getAvailableSeats());
		entity.setStartDate(form.getStartDate());
		entity.setMonths(form.getMonths());
		entity.setStatus(Status.Available);
		entity.setFees(form.getFees());
		entity.setSchedules(form.getSchedules());
		
		entityManager.persist(entity);
		return entity.getId();
	}
	
	@Override
	@Transactional
	public void update(Integer id, ClassesForm form) {
		var entity = entityManager.find(Classes.class, id);
		var course = entityManager.getReference(Course.class, form.getCourseId());
		entity.setCourse(course);
		entity.setAvailableSeats(form.getAvailableSeats());
		entity.setStartDate(form.getStartDate());
		entity.setMonths(form.getMonths());
		entity.setFees(form.getFees());
		entity.setSchedules(form.getSchedules());
	}
}
