package com.jdc.jdbc.repo.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.jdc.jdbc.repo.StudentRepo;
import com.jdc.jdbc.repo.input.ClassSearch;
import com.jdc.jdbc.repo.input.StudentForm;
import com.jdc.jdbc.repo.output.ClassItem;
import com.jdc.jdbc.repo.output.StudentDetails;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class StudentRepoImpl implements StudentRepo {
	
	@Override
	public List<ClassItem> search(ClassSearch form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<StudentDetails> findById(int id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Integer create(StudentForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(int id, StudentForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
