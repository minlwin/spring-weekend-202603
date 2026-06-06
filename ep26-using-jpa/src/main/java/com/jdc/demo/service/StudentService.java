package com.jdc.demo.service;

import java.util.List;

import com.jdc.demo.domain.input.StudentSearch;
import com.jdc.demo.domain.output.StudentDetails;
import com.jdc.demo.domain.output.StudentForRegistration;
import com.jdc.demo.domain.output.StudentListItem;

public interface StudentService {

	List<StudentListItem> search(StudentSearch form);

	StudentDetails findById(int id);

	StudentForRegistration find(String name, String phone, String email);

}
