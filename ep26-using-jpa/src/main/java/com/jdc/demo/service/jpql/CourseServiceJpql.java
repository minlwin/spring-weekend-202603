package com.jdc.demo.service.jpql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.demo.domain.entity.Course;
import com.jdc.demo.domain.input.CourseForm;
import com.jdc.demo.domain.input.CourseSearch;
import com.jdc.demo.domain.output.CourseDetails;
import com.jdc.demo.domain.output.CourseListItem;
import com.jdc.demo.service.CourseService;

import jakarta.persistence.EntityManager;

@Service
@Transactional(readOnly = true)
public class CourseServiceJpql implements CourseService {
	
	@Autowired
	private EntityManager em;
	
	@Override
	@Transactional
	public int create(CourseForm form) {
		return 0;
	}

	@Override
	public List<CourseListItem> search(CourseSearch form) {
		 var query = em.createQuery("select c from Course c", Course.class);
		 var result = query.getResultList();
		 return result.stream().map(CourseListItem::from).toList();
	}

	@Override
	public CourseDetails findById(int id) {
		return null;
	}

	@Override
	@Transactional
	public void update(Integer id, CourseForm form) {
	}

}
