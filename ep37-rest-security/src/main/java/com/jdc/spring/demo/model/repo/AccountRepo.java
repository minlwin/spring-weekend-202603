package com.jdc.spring.demo.model.repo;

import java.util.Optional;

import com.jdc.spring.demo.model.BaseRepository;
import com.jdc.spring.demo.model.entity.Account;

public interface AccountRepo extends BaseRepository<Account, Integer>{

	Optional<Account> findOneByEmail(String email);
}
