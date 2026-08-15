package com.jdc.spring.demo.api.management.service;

import static com.jdc.spring.demo.utils.OptionalsUtils.safeCall;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.spring.demo.api.management.input.CustomerSearch;
import com.jdc.spring.demo.api.management.output.CustomerDetails;
import com.jdc.spring.demo.api.management.output.CustomerListItem;
import com.jdc.spring.demo.model.entity.Customer;
import com.jdc.spring.demo.model.entity.Customer_;
import com.jdc.spring.demo.model.repo.CustomerRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
	
	private final CustomerRepo repo;

	@Transactional(readOnly = true)
	public List<CustomerListItem> search(CustomerSearch search) {
		return repo.search(cb -> {
			var cq = cb.createQuery(CustomerListItem.class);
			var root = cq.from(Customer.class);
			
			CustomerListItem.select(cb, cq, root);
			cq.where(search.where(cb, root));
			
			cq.orderBy(cb.desc(root.get(Customer_.registerdAt)));
			
			return cq;
		});
	}

	@Transactional(readOnly = true)
	public CustomerDetails findById(int id) {
		return safeCall(repo.findById(id).map(CustomerDetails::from))
				.apply("customer").apply("id").apply(id);
	}

}
