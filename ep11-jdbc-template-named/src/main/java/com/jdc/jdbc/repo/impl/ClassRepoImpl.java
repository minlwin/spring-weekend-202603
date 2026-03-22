package com.jdc.jdbc.repo.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.jdc.jdbc.repo.ClassRepo;
import com.jdc.jdbc.repo.input.ClassForm;
import com.jdc.jdbc.repo.input.ClassSearch;
import com.jdc.jdbc.repo.output.ClassDetails;
import com.jdc.jdbc.repo.output.ClassItem;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ClassRepoImpl implements ClassRepo {
		
	@Override
	public List<ClassItem> search(ClassSearch form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ClassDetails> findById(int id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Integer create(ClassForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(int id, ClassForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
