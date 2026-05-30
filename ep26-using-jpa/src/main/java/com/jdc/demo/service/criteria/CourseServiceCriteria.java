package com.jdc.demo.service.criteria;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.jdc.demo.domain.input.CourseSearch;
import com.jdc.demo.domain.output.CourseListItem;
import com.jdc.demo.service.base.AbstractCourseService;

@Service
@Profile("criteria")
public class CourseServiceCriteria extends AbstractCourseService{

	@Override
	public List<CourseListItem> search(CourseSearch form) {
		// TODO Auto-generated method stub
		return null;
	}


}
