package com.jdc.spring.demo.model.repo;

import java.util.Optional;

import com.jdc.spring.demo.model.BaseRepository;
import com.jdc.spring.demo.model.entity.Candidate;

public interface CandidateRepo extends BaseRepository<Candidate, Integer>{

	Optional<Candidate> findOneByAccountEmail(String username);

}
