package com.jdc.jdbc.repo.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.jdc.jdbc.repo.RegistrationRepo;
import com.jdc.jdbc.repo.input.RegistrationSearch;
import com.jdc.jdbc.repo.output.RegistrationItem;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RegistrationRepoImpl implements RegistrationRepo {
	
	
	@Override
	public String create(int classId, int studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<RegistrationItem> findById(String code) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<RegistrationItem> search(RegistrationSearch form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(String code) {
		// TODO Auto-generated method stub
		return 0;
	}

}
