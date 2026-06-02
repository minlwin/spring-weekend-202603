package com.jdc.demo.service.base;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.demo.domain.entity.Registration;
import com.jdc.demo.domain.output.RegistrationDetails;
import com.jdc.demo.service.RegistrationService;

import jakarta.persistence.EntityManager;

public abstract class AbstractRegistrationService implements RegistrationService {
	
	@Autowired
	protected EntityManager entityManager;

	@Override
	@Transactional(readOnly = true)
	public RegistrationDetails findById(UUID id) {
		var entity = entityManager.find(Registration.class, id);
		return RegistrationDetails.from(entity);
	}
}
