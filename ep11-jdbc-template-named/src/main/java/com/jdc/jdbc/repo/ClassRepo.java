package com.jdc.jdbc.repo;

import java.util.List;
import java.util.Optional;

import com.jdc.jdbc.repo.input.ClassForm;
import com.jdc.jdbc.repo.input.ClassSearch;
import com.jdc.jdbc.repo.output.ClassDetails;
import com.jdc.jdbc.repo.output.ClassItem;

public interface ClassRepo {

	List<ClassItem> search(ClassSearch form);

	Optional<ClassDetails> findById(int id);
	
	Integer create(ClassForm form);

	Integer update(int id, ClassForm form);
	
	Integer delete(int id);
}
