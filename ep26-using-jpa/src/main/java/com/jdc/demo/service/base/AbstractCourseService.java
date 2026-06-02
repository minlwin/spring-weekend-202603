package com.jdc.demo.service.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.demo.domain.entity.Course;
import com.jdc.demo.domain.input.CourseForm;
import com.jdc.demo.domain.output.CourseDetails;
import com.jdc.demo.service.CourseService;

import jakarta.persistence.EntityManager;

public abstract class AbstractCourseService implements CourseService{

	@Autowired
	protected EntityManager em;
	
	@Override
	@Transactional
	public int create(CourseForm form) {
		var entity = form.entity();
		em.persist(entity);
		return entity.getId();
	}

	@Override
	@Transactional(readOnly = true)
	public CourseDetails findById(int id) {
		return CourseDetails.from(em.find(Course.class, id));
	}

	@Override
	@Transactional
	public void update(Integer id, CourseForm form) {
		var entity = em.find(Course.class, id);
		
		if(null != entity) {
			entity.setName(form.getName());
			entity.setHours(form.getHours());
			entity.setLevel(form.getLevel());
			entity.setDescription(form.getDescription());
		}
	}

}
