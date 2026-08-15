package com.jdc.spring.demo.api.management.input;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.jdc.spring.demo.model.entity.Account_;
import com.jdc.spring.demo.model.entity.Customer;
import com.jdc.spring.demo.model.entity.Customer_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record CustomerSearch(
		LocalDate dateFrom, 
		LocalDate dateTo,
		String keyword) {

	public List<Predicate> where(CriteriaBuilder cb, Root<Customer> root) {
		var params = new ArrayList<Predicate>();
		
		if(null != dateFrom) {
			params.add(cb.greaterThanOrEqualTo(root.get(Customer_.registerdAt), 
					dateFrom.atStartOfDay()));
		}
		
		if(null != dateTo) {
			params.add(cb.lessThan(root.get(Customer_.registerdAt), 
					dateTo.plusDays(1).atStartOfDay()));
		}
		
		if(StringUtils.hasLength(keyword)) {
			var account = root.get(Customer_.account);
			var param = keyword.toLowerCase().concat("%");
			params.add(cb.or(
				cb.like(cb.lower(account.get(Account_.name)), param),
				cb.like(cb.lower(account.get(Account_.email)), param),
				cb.like(cb.lower(root.get(Customer_.phone)), param)
			));
		}
		
		return params;
	}

}
