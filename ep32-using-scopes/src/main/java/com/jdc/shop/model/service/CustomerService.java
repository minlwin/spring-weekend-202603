package com.jdc.shop.model.service;

import java.util.function.Function;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.shop.controller.management.input.CustomerSearch;
import com.jdc.shop.controller.management.output.CustomerListItem;
import com.jdc.shop.model.PageResult;
import com.jdc.shop.model.entity.Customer;
import com.jdc.shop.model.entity.Customer_;
import com.jdc.shop.model.repository.CustomerRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
	
	private final CustomerRepo repo;

	@Transactional(readOnly = true)
	public PageResult<CustomerListItem> search(CustomerSearch form, int page, int size) {
		return repo.search(queryFunc(form), countFunc(form), page, size);
	}
	
	private Function<CriteriaBuilder, CriteriaQuery<CustomerListItem>> queryFunc(CustomerSearch form) {
		return cb -> {
			var cq = cb.createQuery(CustomerListItem.class);
			var root = cq.from(Customer.class);
			
			CustomerListItem.select(cq, cb, root);
			cq.where(form.where(cb, root));
			cq.orderBy(cb.desc(root.get(Customer_.entryAt)));
			
			return cq;
		};
	}

	private Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc(CustomerSearch form) {
		return cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(Customer.class);
			
			cq.select(cb.count(root));
			cq.where(form.where(cb, root));
			
			return cq;
		};
	}
}
