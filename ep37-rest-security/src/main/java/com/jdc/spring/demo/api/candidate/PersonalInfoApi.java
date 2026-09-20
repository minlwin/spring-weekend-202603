package com.jdc.spring.demo.api.candidate;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.spring.demo.api.candidate.input.PersonalInfoForm;
import com.jdc.spring.demo.api.candidate.output.PersonalInformation;
import com.jdc.spring.demo.api.candidate.service.PersonalInfoService;
import com.jdc.spring.demo.model.ModificationResult;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("candidate/personal-info")
public class PersonalInfoApi {
	
	private final PersonalInfoService service;

	@GetMapping("{username}")
	@PreAuthorize("#username eq authentication.name")
	PersonalInformation getPersonalInfo(@PathVariable String username) {
		return service.find(username);
	}
	
	@PutMapping("{id}")
	ModificationResult<Integer> update(
			@PathVariable int id,
			@Validated @RequestBody PersonalInfoForm form) {
		return service.update(id, form);
	}
	
	@PutMapping("{id}/photo")
	ModificationResult<Integer> uploadPhoto(
			@PathVariable int id,
			@RequestParam MultipartFile file) {
		return service.uploadPhoto(id, file);
	}
	
}
