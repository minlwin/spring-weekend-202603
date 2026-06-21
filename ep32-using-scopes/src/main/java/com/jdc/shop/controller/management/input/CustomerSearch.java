package com.jdc.shop.controller.management.input;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.jdc.shop.model.entity.Account_;
import com.jdc.shop.model.entity.Customer;
import com.jdc.shop.model.entity.Customer_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

@Data
public class CustomerSearch {

	private LocalDate from;
	private LocalDate to;
	private String keyword;
	
	public List<Predicate> where(CriteriaBuilder cb, Root<Customer> root) {
		
		var params = new ArrayList<Predicate>();
		
		if(null != from) {
			params.add(cb.greaterThanOrEqualTo(
				root.get(Customer_.entryAt), 
				from.atStartOfDay()
			));
		}
		
		if(null != to) {
			params.add(cb.lessThan(
				root.get(Customer_.entryAt), 
				to.plusDays(1).atStartOfDay()
			));
		}
		
		if(StringUtils.hasLength(keyword)) {
			var param = keyword.toLowerCase().concat("%");
			params.add(cb.or(
				cb.like(cb.lower(root.get(Customer_.name)), param),
				cb.like(cb.lower(root.get(Customer_.phone)), param),
				cb.like(cb.lower(root.get(Customer_.account).get(Account_.email)), param)
			));
		}

		return params;
	}
}
