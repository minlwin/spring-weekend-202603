package com.jdc.spring.demo.api.candidate.service;

import static com.jdc.spring.demo.utils.OptionalsUtils.safeCall;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.spring.demo.api.candidate.input.PersonalInfoForm;
import com.jdc.spring.demo.api.candidate.output.PersonalInformation;
import com.jdc.spring.demo.model.ModificationResult;
import com.jdc.spring.demo.model.repo.AccountRepo;
import com.jdc.spring.demo.model.repo.CandidateRepo;
import com.jdc.spring.demo.model.service.ProfileStorageService;
import com.jdc.spring.demo.utils.exceptions.BusinessRuleViolationException;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonalInfoService {
	
	private final CandidateRepo candidateRepo;
	private final AccountRepo accountRepo;
	private final ProfileStorageService storageService;
	
	@Transactional(readOnly = true)
	public PersonalInformation find(String username) {
		return safeCall(candidateRepo.findOneByAccountEmail(username).map(PersonalInformation::from))
				.apply("Candidate")
				.apply("email")
				.apply(username);
	}

	public ModificationResult<Integer> update(int id, PersonalInfoForm form) {
		
		var entity = safeCall(candidateRepo.findById(id))
				.apply("candidate")
				.apply("id")
				.apply(id);
		
		if(!entity.getAccount().getEmail().equals(form.email())) {
			var account = accountRepo.findOneByEmail(form.email()).orElse(null);
			if(null != account) {
				throw new BusinessRuleViolationException("%s is already used by other user.".formatted(form.email()));
			}
		}
		
		form.setValues(entity);
		
		return new ModificationResult<Integer>(id);
	}

	public ModificationResult<Integer> uploadPhoto(int id, MultipartFile file) {
		
		var entity = safeCall(candidateRepo.findById(id))
				.apply("candidate")
				.apply("id")
				.apply(id);

		var profileImage = storageService.save(id, file);
		
		entity.setSelfie(profileImage);
		
		return new ModificationResult<Integer>(id);
	}

}
