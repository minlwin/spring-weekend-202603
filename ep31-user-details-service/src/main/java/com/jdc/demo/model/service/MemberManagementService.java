package com.jdc.demo.model.service;

import java.util.List;
import java.util.function.Function;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.demo.exceptions.AppBusinessException;
import com.jdc.demo.model.entity.Account_;
import com.jdc.demo.model.entity.Member;
import com.jdc.demo.model.entity.Member_;
import com.jdc.demo.model.input.ProfileForm;
import com.jdc.demo.model.output.MemberListItem;
import com.jdc.demo.model.output.ProfileDto;
import com.jdc.demo.model.repo.MemberRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberManagementService {
	
	private final MemberRepo repo;
	private final ProfileImageService imageService;

	@PreAuthorize("hasRole('Admin')")
	@Transactional(readOnly = true)
	public List<MemberListItem> getAll() {
		
		Function<CriteriaBuilder, CriteriaQuery<MemberListItem>> queryFunc = cb -> {
			var cq = cb.createQuery(MemberListItem.class);
			var member = cq.from(Member.class);
			var account = member.get(Member_.account);
			
			cq.select(cb.construct(
					MemberListItem.class, 
					member.get(Member_.id),
					member.get(Member_.name),
					account.get(Account_.email),
					member.get(Member_.phone),
					account.get(Account_.activated)
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
		return StringUtils.hasLength(member.getPhone())
				&& StringUtils.hasLength(member.getAddress())
				&& StringUtils.hasLength(member.getName());
	}
	
	@PostAuthorize("authentication.name eq returnObj.account.email")
	public Member findByEmail(String email) {
		return repo.findOneByAccountEmail(email)
				.orElseThrow(() -> new AppBusinessException("There is no member with %s".formatted(email)));
	}

	
	@Transactional
	@PreAuthorize("hasRole('Member') and authentication.name eq #email")
	public void updateProfile(String email, ProfileForm form) {
		var member = repo.findOneByAccountEmail(email)
				.orElseThrow(() -> new AppBusinessException("There is no member with %s".formatted(email)));
		
		member.setName(form.getName());
		member.setPhone(form.getPhone());
		member.setAddress(form.getAddress());
	}

	@Transactional
	@PreAuthorize("hasRole('Member') and authentication.name eq #email")
	public void uploadProfileImage(String email, MultipartFile file) {
		var member = repo.findOneByAccountEmail(email)
				.orElseThrow(() -> new AppBusinessException("There is no member with %s".formatted(email)));
		
		var imagePath = imageService.upload(member.getId(), file);
		
		member.setProfileImage(imagePath);
	}

	public ProfileDto getProfile() {
		var email = SecurityContextHolder.getContext().getAuthentication().getName();
		return repo.findOneByAccountEmail(email)
				.map(ProfileDto::from)
				.orElseThrow(() -> new AppBusinessException("There is no member with %s".formatted(email)));
	}

}
