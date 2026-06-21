package com.jdc.shop.controller.management.input;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.jdc.shop.model.entity.Account_;
import com.jdc.shop.model.entity.Employee;
import com.jdc.shop.model.entity.Employee_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

@Data
public class EmployeeSearch {

	private LocalDate from;
	private LocalDate to;
	private String keyword;
	
	public List<Predicate> where(CriteriaBuilder cb, Root<Employee> root) {
		var params = new ArrayList<Predicate>();
		
		if(null != from) {
			params.add(cb.greaterThanOrEqualTo(
				root.get(Employee_.entryAt), 
				from.atStartOfDay()
			));
		}
		
		if(null != to) {
			params.add(cb.lessThan(
				root.get(Employee_.entryAt), 
				to.plusDays(1).atStartOfDay()
			));
		}
		
		if(StringUtils.hasLength(keyword)) {
			var param = keyword.toLowerCase().concat("%");
			params.add(cb.or(
				cb.like(cb.lower(root.get(Employee_.name)), param),
				cb.like(cb.lower(root.get(Employee_.phone)), param),
				cb.like(cb.lower(root.get(Employee_.account).get(Account_.email)), param)
			));
		}

		return params;
	}

}
