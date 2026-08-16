package com.jdc.spring.demo.model.repo;

import java.util.Optional;

import com.jdc.spring.demo.model.BaseRepository;
import com.jdc.spring.demo.model.entity.Customer;

public interface CustomerRepo extends BaseRepository<Customer, Integer>{
	
	Optional<Customer> findOneByAccountEmail(String email);
}
