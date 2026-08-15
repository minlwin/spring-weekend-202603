package com.jdc.spring.demo.api.management.output;

import java.time.LocalDateTime;

import com.jdc.spring.demo.model.entity.Account_;
import com.jdc.spring.demo.model.entity.Customer;
import com.jdc.spring.demo.model.entity.Customer_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record CustomerListItem(
		int id,
		String name,
		String email,
		String phone,
		LocalDateTime registeredAt) {

	public static void select(CriteriaBuilder cb, CriteriaQuery<CustomerListItem> cq, Root<Customer> root) {
		var account = root.get(Customer_.account);
		
		cq.select(cb.construct(
			CustomerListItem.class, 
			root.get(Customer_.id),
			account.get(Account_.name),
			account.get(Account_.email),
			root.get(Customer_.phone),
			root.get(Customer_.registerdAt)
		));
	}

}
