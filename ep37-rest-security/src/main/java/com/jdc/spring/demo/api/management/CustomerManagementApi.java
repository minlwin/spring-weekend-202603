package com.jdc.spring.demo.api.management;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.spring.demo.api.management.input.CustomerSearch;
import com.jdc.spring.demo.api.management.output.CustomerDetails;
import com.jdc.spring.demo.api.management.output.CustomerListItem;

@RestController
@RequestMapping("management/customers")
public class CustomerManagementApi {

	@GetMapping
	List<CustomerListItem> search(CustomerSearch search) {
		return null;
	}
	
	@GetMapping("{id}")
	CustomerDetails findById(@PathVariable int id) {
		return null;
	}
}
