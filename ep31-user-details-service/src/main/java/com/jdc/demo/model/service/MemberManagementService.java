package com.jdc.demo.model.service;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.demo.exceptions.AppBusinessException;
import com.jdc.demo.model.entity.Member;
import com.jdc.demo.model.output.MemberListItem;
import com.jdc.demo.model.repo.MemberRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
public class MemberManagementService {
	
	@Autowired
	private MemberRepo repo;

	@PreAuthorize("hasRole('Admin')")
	@Transactional(readOnly = true)
	public List<MemberListItem> getAll() {
		
		Function<CriteriaBuilder, CriteriaQuery<MemberListItem>> queryFunc = cb -> {
			var cq = cb.createQuery(MemberListItem.class);
			var member = cq.from(Member.class);
			var account = member.get("account");
			
			cq.select(cb.construct(
					MemberListItem.class, 
					member.get("id"),
					member.get("name"),
					account.get("email"),
					member.get("phone"),
					account.get("activated")
				));
			
			cq.orderBy(cb.asc(member.get("id")));
			
			return cq;
		};
		
		return repo.search(queryFunc);
	}

	@Transactional
	@PreAuthorize("hasRole('Admin')")
	public void switchStatus(int id) {
		repo.findById(id).ifPresent(member -> {
			var account = member.getAccount();
			account.setActivated(!account.isActivated());
		});
	}

	@PreAuthorize("hasRole('Admin') or (hasRole('Member') and authentication.name eq #name)")
	public boolean doesEditProfile(String name) {
		var member = repo.findOneByAccountEmail(name)
				.orElseThrow(() -> new AppBusinessException("There is no member with %s".formatted(name)));
		return !StringUtils.hasLength(member.getPhone())
				|| !StringUtils.hasLength(member.getAddress())
				|| !StringUtils.hasLength(member.getProfileImage());
	}

}
