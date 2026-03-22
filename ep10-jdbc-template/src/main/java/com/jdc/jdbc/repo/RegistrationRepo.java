package com.jdc.jdbc.repo;

import java.util.Optional;

import com.jdc.jdbc.repo.output.RegistrationItem;

public interface RegistrationRepo {

	String create(int classId, int studentId);
	
	Optional<RegistrationItem> findById(String code);

}
