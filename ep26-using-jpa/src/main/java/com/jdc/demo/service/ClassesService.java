package com.jdc.demo.service;

import java.util.List;

import com.jdc.demo.domain.input.ClassesSearch;
import com.jdc.demo.domain.output.ClassesDetails;
import com.jdc.demo.domain.output.ClassesListItem;

public interface ClassesService {

	List<ClassesListItem> search(ClassesSearch form);

	ClassesDetails findById(int id);

}
