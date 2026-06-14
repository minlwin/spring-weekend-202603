package com.jdc.demo.model.repo;

import java.util.Optional;

import com.jdc.demo.model.BaseRepository;
import com.jdc.demo.model.entity.Account;

public interface AccountRepo extends BaseRepository<Account, Integer>{

	Optional<Account> findOneByEmail(String email);
	
	long countByEmail(String email);
}
