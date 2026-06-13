package com.jdc.demo.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.demo.model.entity.Member;

public interface MemberRepo extends JpaRepository<Member, Integer>{

}
