package com.jdc.demo.service;

import java.util.List;
import java.util.UUID;

import com.jdc.demo.domain.input.RegistrationSearch;
import com.jdc.demo.domain.output.RegistrationDetails;
import com.jdc.demo.domain.output.RegistrationListItem;

public interface RegistrationService {

	List<RegistrationListItem> search(RegistrationSearch form);

	RegistrationDetails findById(UUID id);

}
