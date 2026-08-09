package com.jdc.spring.demo.api.member;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.spring.demo.api.management.output.CustomerDetails;
import com.jdc.spring.demo.api.member.input.ProfileForm;
import com.jdc.spring.demo.model.ModificationResult;

@RestController
@RequestMapping("member/profile")
public class MemberProfileApi {

	@GetMapping
	CustomerDetails profile() {
		return null;
	}
	
	@PatchMapping
	ModificationResult<Integer> update(@RequestBody ProfileForm form) {
		return null;
	}
}
