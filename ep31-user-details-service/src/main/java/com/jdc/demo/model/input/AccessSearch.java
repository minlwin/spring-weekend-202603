package com.jdc.demo.model.input;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.jdc.demo.model.entity.AccessHistory;
import com.jdc.demo.model.entity.AccessHistory.AccessType;
import com.jdc.demo.model.entity.AccessHistory.Status;
import com.jdc.demo.model.entity.AccessHistory_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

@Data
public class AccessSearch {

	private AccessType type;
	private Status status;
	private LocalDate from;
	private LocalDate to;
	
	public List<Predicate> where(CriteriaBuilder cb, Root<AccessHistory> root) {
		var params = new ArrayList<Predicate>();
		
		if(null != type) {
			params.add(cb.equal(root.get(AccessHistory_.type), type));
		}

		if(null != status) {
			params.add(cb.equal(root.get(AccessHistory_.status), status));
		}

		if(null != from) {
			params.add(cb.greaterThanOrEqualTo(root.get(AccessHistory_.accessAt), from.atStartOfDay()));
		}

		if(null != to) {
			params.add(cb.lessThan(root.get(AccessHistory_.accessAt), to.plusDays(1).atStartOfDay()));
		}
		
		return params;
	}
}
