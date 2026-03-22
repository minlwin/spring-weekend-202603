package com.jdc.jdbc.repo;

import java.util.List;
import java.util.Optional;

import com.jdc.jdbc.repo.input.CourseForm;
import com.jdc.jdbc.repo.input.CourseSearch;
import com.jdc.jdbc.repo.output.CourseDetails;
import com.jdc.jdbc.repo.output.CourseItem;

public interface CourseRepo {

	Integer create(CourseForm form);
	
	Optional<CourseDetails> findById(int id);
	
	List<CourseItem> search(CourseSearch form);
	
	int update(int id, CourseForm form);
	
	int delete(int id);

}
