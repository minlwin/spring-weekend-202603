package com.jdc.demo.service.jpql;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.demo.domain.input.RegistrationSearch;
import com.jdc.demo.domain.output.RegistrationListItem;
import com.jdc.demo.service.base.AbstractRegistrationService;

@Service
@Profile("jpql")
public class RegistrationServiceJpql extends AbstractRegistrationService{

	@Override
	@Transactional(readOnly = true)
	public List<RegistrationListItem> search(RegistrationSearch form) {
		// TODO Auto-generated method stub
		return null;
	}

}
