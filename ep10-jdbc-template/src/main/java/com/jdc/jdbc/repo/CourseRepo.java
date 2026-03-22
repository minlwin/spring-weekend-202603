package com.jdc.jdbc.repo;

import java.util.List;
import java.util.Optional;

import com.jdc.jdbc.repo.input.CourseForm;
import com.jdc.jdbc.repo.input.CourseSearch;
import com.jdc.jdbc.repo.output.CourseDetails;
import com.jdc.jdbc.repo.output.CourseItem;

public interface CourseRepo {

	List<CourseItem> search(CourseSearch form);
	
	Optional<CourseDetails> findById(int id);
	
	Integer create(CourseForm form);
	
	int update(int id, CourseForm form);
	
	int delete(int id);
}
