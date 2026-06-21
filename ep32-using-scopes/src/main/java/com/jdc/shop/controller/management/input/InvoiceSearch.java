package com.jdc.shop.controller.management.input;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import com.jdc.shop.model.entity.Account_;
import com.jdc.shop.model.entity.Customer_;
import com.jdc.shop.model.entity.Invoice;
import com.jdc.shop.model.entity.Invoice.Status;
import com.jdc.shop.model.entity.Invoice_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

@Data
public class InvoiceSearch {

	private Status status;
	private LocalDate from;
	private LocalDate to;
	private String keyword;

	public List<Predicate> where(CriteriaBuilder cb, Root<Invoice> root) {
		
		var params = new ArrayList<Predicate>();
		
		var authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication.getAuthorities().stream().map(a -> a.getAuthority()).toList().contains("Customer")) {
			params.add(cb.equal(root.get(Invoice_.customer).get(Customer_.account).get(Account_.email), authentication.getName()));
		}

		if (null != status) {
			params.add(cb.equal(root.get(Invoice_.status), status));
		}

		if (null != from) {
			params.add(cb.greaterThanOrEqualTo(root.get(Invoice_.invoiceAt), from.atStartOfDay()));
		}

		if (null != to) {
			params.add(cb.lessThan(root.get(Invoice_.invoiceAt), to.plusDays(1).atStartOfDay()));
		}

		if (StringUtils.hasLength(keyword)) {
			var param = keyword.toLowerCase().concat("%");
			var customer = root.get(Invoice_.customer);
			params.add(cb.or(
				cb.like(cb.lower(customer.get(Customer_.name)), param),
				cb.like(cb.lower(customer.get(Customer_.phone)), param),
				cb.like(cb.lower(customer.get(Customer_.account).get(Account_.email)), param)
			));
		}

		return params;
	}
}
