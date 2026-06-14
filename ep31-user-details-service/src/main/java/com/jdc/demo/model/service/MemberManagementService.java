package com.jdc.demo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.demo.model.output.MemberListItem;
import com.jdc.demo.model.repo.MemberRepo;

@Service
public class MemberManagementService {
	
	@Autowired
	private MemberRepo repo;

	@PreAuthorize("hasRole('Admin')")
	@Transactional(readOnly = true)
	public List<MemberListItem> getAll() {
		return repo.searchAll();
	}

	@Transactional
	public void switchStatus(int id) {
		repo.findById(id).ifPresent(member -> {
			var account = member.getAccount();
			account.setActivated(!account.isActivated());
		});
	}

}
