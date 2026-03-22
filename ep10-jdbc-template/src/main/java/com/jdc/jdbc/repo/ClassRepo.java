package com.jdc.jdbc.repo;

import java.util.Optional;

import com.jdc.jdbc.repo.input.ClassForm;
import com.jdc.jdbc.repo.output.ClassDetails;

public interface ClassRepo {

	Optional<ClassDetails> findById(int id);
	
	Integer create(ClassForm form);

}
