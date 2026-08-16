package com.jdc.spring.demo.api.member;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.spring.demo.api.management.output.CustomerDetails;
import com.jdc.spring.demo.api.member.input.ProfileForm;
import com.jdc.spring.demo.api.member.service.MemberProfileService;
import com.jdc.spring.demo.model.ModificationResult;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("member/profile")
public class MemberProfileApi {
	
	private final MemberProfileService service;

	@GetMapping
	CustomerDetails profile() {
		var username = SecurityContextHolder.getContext().getAuthentication().getName();
		return service.getProfile(username);
	}
	
	@PatchMapping
	ModificationResult<Integer> update(@Validated @RequestBody ProfileForm form) {
		var username = SecurityContextHolder.getContext().getAuthentication().getName();
		return service.update(username, form);
	}
}
