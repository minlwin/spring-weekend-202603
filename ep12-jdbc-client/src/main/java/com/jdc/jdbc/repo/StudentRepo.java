package com.jdc.jdbc.repo;

import java.util.List;
import java.util.Optional;

import com.jdc.jdbc.repo.input.ClassSearch;
import com.jdc.jdbc.repo.input.StudentForm;
import com.jdc.jdbc.repo.output.ClassItem;
import com.jdc.jdbc.repo.output.StudentDetails;

public interface StudentRepo {

	List<ClassItem> search(ClassSearch form);

	Optional<StudentDetails> findById(int id);
	
	Integer create(StudentForm form);

	Integer update(int id, StudentForm form);
	
	Integer delete(int id);
	
}
