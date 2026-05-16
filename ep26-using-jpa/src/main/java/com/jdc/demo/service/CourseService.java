package com.jdc.demo.service;

import java.util.List;

import com.jdc.demo.domain.input.CourseForm;
import com.jdc.demo.domain.input.CourseSearch;
import com.jdc.demo.domain.output.CourseDetails;
import com.jdc.demo.domain.output.CourseListItem;

public interface CourseService {

	int create(CourseForm form);

	List<CourseListItem> search(CourseSearch form);

	CourseDetails findById(int id);

	void update(Integer id, CourseForm form);

}