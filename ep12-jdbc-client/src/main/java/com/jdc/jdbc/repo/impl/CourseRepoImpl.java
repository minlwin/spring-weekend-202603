package com.jdc.jdbc.repo.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.jdc.jdbc.repo.CourseRepo;
import com.jdc.jdbc.repo.input.CourseForm;
import com.jdc.jdbc.repo.input.CourseSearch;
import com.jdc.jdbc.repo.output.CourseDetails;
import com.jdc.jdbc.repo.output.CourseItem;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CourseRepoImpl implements CourseRepo {
	
	
	@Override
	public List<CourseItem> search(CourseSearch form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CourseDetails> findById(int id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Integer create(CourseForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(int id, CourseForm form) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
