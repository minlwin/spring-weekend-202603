package com.jdc.demo.model.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jdc.demo.model.entity.Member;
import com.jdc.demo.model.output.MemberListItem;

public interface MemberRepo extends JpaRepository<Member, Integer>{

	@Query("select new com.jdc.demo.model.output.MemberListItem(m) from Member m")
	List<MemberListItem> searchAll();
}
