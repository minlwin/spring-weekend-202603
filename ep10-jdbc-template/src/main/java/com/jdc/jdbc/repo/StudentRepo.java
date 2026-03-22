package com.jdc.jdbc.repo;

import java.util.Optional;

import com.jdc.jdbc.repo.output.StudentDetails;

public interface StudentRepo {

	Optional<StudentDetails> findById(int id);
}
