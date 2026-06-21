package com.jdc.shop.model.service;

import org.springframework.stereotype.Service;

import com.jdc.shop.controller.management.input.CustomerSearch;
import com.jdc.shop.controller.management.output.CustomerListItem;
import com.jdc.shop.model.PageResult;

@Service
public class CustomerService {

	public PageResult<CustomerListItem> search(CustomerSearch form, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

}
