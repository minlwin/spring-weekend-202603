package com.jdc.demo.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jdc.demo.domain.input.CourseForm;
import com.jdc.demo.domain.input.CourseSearch;
import com.jdc.demo.domain.output.CourseDetails;
import com.jdc.demo.domain.output.CourseListItem;

@Transactional(readOnly = true)
public interface CourseService {

	@Transactional
	int create(CourseForm form);

	@Transactional
	void update(Integer id, CourseForm form);

	List<CourseListItem> search(CourseSearch form);

	CourseDetails findById(int id);

}