package com.jdc.spring.demo.api.management.input;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.jdc.spring.demo.model.entity.Account_;
import com.jdc.spring.demo.model.entity.Employee;
import com.jdc.spring.demo.model.entity.Employee_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record EmployeeSearch(
		Boolean activated, 
		String keyword) {

	public List<Predicate> where(CriteriaBuilder cb, Root<Employee> root) {
		
		var params = new ArrayList<Predicate>();
		
		if(null != activated) {
			params.add(activated ? cb.isNotNull(root.get(Employee_.activatedAt)) : cb.isNull(root.get(Employee_.activatedAt)));
		}
		
		if(StringUtils.hasLength(keyword)) {
			var account = root.get(Employee_.account);
			var param = keyword.toLowerCase().concat("%");
			params.add(cb.or(
				cb.like(cb.lower(account.get(Account_.name)), param),
				cb.like(cb.lower(account.get(Account_.email)), param),
				cb.like(cb.lower(root.get(Employee_.phone)), param)
			));
		}
		
		return params;
	}

}
