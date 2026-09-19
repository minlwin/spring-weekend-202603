package com.jdc.spring.demo.api.management.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.spring.demo.api.management.input.PartnerSearch;
import com.jdc.spring.demo.api.management.output.PartnerListItem;
import com.jdc.spring.demo.model.entity.Partner;
import com.jdc.spring.demo.model.entity.PartnerCompany;
import com.jdc.spring.demo.model.entity.PartnerCompany_;
import com.jdc.spring.demo.model.entity.Partner_;
import com.jdc.spring.demo.model.repo.PartnerRepo;

import jakarta.persistence.criteria.JoinType;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PartnerService {
	
	private final PartnerRepo partnerRepo;

	@Transactional(readOnly = true)
	public List<PartnerListItem> search(PartnerSearch form) {
		return partnerRepo.search(cb -> {
			var cq = cb.createQuery(PartnerListItem.class);
			
			var root = cq.from(Partner.class);
			var company = root.join(PartnerCompany.class, JoinType.LEFT);
			company.on(cb.equal(root, company.get(PartnerCompany_.partner)));
			
			PartnerListItem.select(cb, cq, root, company);
			
			cq.where(form.where(cb, root, company));
			cq.orderBy(cb.desc(root.get(Partner_.registerdAt)));
			
			return cq;
		});
	}

}
