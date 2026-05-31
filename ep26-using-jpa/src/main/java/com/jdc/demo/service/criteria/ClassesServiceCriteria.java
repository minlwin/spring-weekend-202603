package com.jdc.demo.service.criteria;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.jdc.demo.domain.input.ClassesSearch;
import com.jdc.demo.domain.output.ClassesListItem;
import com.jdc.demo.service.base.AbstractClassesService;

@Service
@Profile("criteria")
public class ClassesServiceCriteria extends AbstractClassesService {

	@Override
	public List<ClassesListItem> search(ClassesSearch form) {
		// TODO Auto-generated method stub
		return null;
	}

}
