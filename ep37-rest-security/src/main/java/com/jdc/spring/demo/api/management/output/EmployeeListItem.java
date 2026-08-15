package com.jdc.spring.demo.api.management.output;

import java.time.LocalDateTime;

import com.jdc.spring.demo.model.entity.Account_;
import com.jdc.spring.demo.model.entity.Employee;
import com.jdc.spring.demo.model.entity.Employee_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record EmployeeListItem(
		int id,
		String name,
		String email,
		String phone,
		LocalDateTime activatedAt) {
	
	public boolean isActivated() {
		return activatedAt != null;
	}

	public static void select(CriteriaBuilder cb, CriteriaQuery<EmployeeListItem> cq, Root<Employee> root) {
		var account = root.get(Employee_.account);
		cq.select(cb.construct(EmployeeListItem.class, 
			root.get(Employee_.id),
			account.get(Account_.name),
			account.get(Account_.email),
			root.get(Employee_.phone),
			root.get(Employee_.activatedAt)));
	}

}
