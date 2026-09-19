package com.jdc.spring.demo.api.management.output;

import java.time.LocalDateTime;

import com.jdc.spring.demo.model.entity.Account_;
import com.jdc.spring.demo.model.entity.Partner;
import com.jdc.spring.demo.model.entity.PartnerCompany;
import com.jdc.spring.demo.model.entity.PartnerCompany_;
import com.jdc.spring.demo.model.entity.Partner_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

public record PartnerListItem(
		int id,
		String name,
		LocalDateTime registerdAt,
		LocalDateTime activatedAt,
		String company,
		String email,
		String phone) {

	public static void select(CriteriaBuilder cb, CriteriaQuery<PartnerListItem> cq, Root<Partner> root, Join<Partner, PartnerCompany> joinCompany) {
		cq.select(cb.construct(PartnerListItem.class, 
			root.get(Partner_.id),
			root.get(Partner_.account).get(Account_.name),
			root.get(Partner_.registerdAt),
			root.get(Partner_.verifiedAt),
			joinCompany.get(PartnerCompany_.name),
			root.get(Partner_.account).get(Account_.email),
			root.get(Partner_.phone)
		));
	}

}
