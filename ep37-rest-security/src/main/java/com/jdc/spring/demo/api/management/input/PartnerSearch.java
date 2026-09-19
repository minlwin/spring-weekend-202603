package com.jdc.spring.demo.api.management.input;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.jdc.spring.demo.model.entity.Account_;
import com.jdc.spring.demo.model.entity.Partner;
import com.jdc.spring.demo.model.entity.PartnerCompany;
import com.jdc.spring.demo.model.entity.PartnerCompany_;
import com.jdc.spring.demo.model.entity.Partner_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record PartnerSearch(
		Boolean status,
		LocalDate registerFrom,
		LocalDate registerTo,
		String keyword) {

	public List<Predicate> where(CriteriaBuilder cb, Root<Partner> root, Join<Partner, PartnerCompany> company) {
		
		var params = new ArrayList<Predicate>();
		
		if(null != status) {
			params.add(status ? cb.isNotNull(root.get(Partner_.verifiedAt)) : cb.isNull(root.get(Partner_.verifiedAt)));
		}
		
		if(null != registerFrom) {
			params.add(cb.greaterThanOrEqualTo(root.get(Partner_.registerdAt), registerFrom.atStartOfDay()));
		}
		
		if(null != registerTo) {
			params.add(cb.lessThan(root.get(Partner_.registerdAt), registerTo.plusDays(1).atStartOfDay()));
		}
		
		if(StringUtils.hasLength(keyword)) {
			var param = keyword.toLowerCase().concat("%");
			params.add(cb.or(
				cb.like(root.get(Partner_.account).get(Account_.name), param),
				cb.like(company.get(PartnerCompany_.name), param),
				cb.like(root.get(Partner_.account).get(Account_.email), param),
				cb.like(root.get(Partner_.phone), param)
			));
		}

		return params;
	}

}
