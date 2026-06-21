package com.jdc.shop.model.service;

import org.springframework.stereotype.Service;

import com.jdc.shop.controller.management.input.EmployeeForm;
import com.jdc.shop.controller.management.input.EmployeeSearch;
import com.jdc.shop.controller.management.output.EmployeeListItem;
import com.jdc.shop.model.PageResult;

@Service
public class EmployeeService {

	public PageResult<EmployeeListItem> search(EmployeeSearch form, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public void create(EmployeeForm form) {
		// TODO Auto-generated method stub
		
	}

}
