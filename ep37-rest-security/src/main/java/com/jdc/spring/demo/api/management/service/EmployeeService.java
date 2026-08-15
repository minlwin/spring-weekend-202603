package com.jdc.spring.demo.api.management.service;

import static com.jdc.spring.demo.utils.OptionalsUtils.safeCall;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jdc.spring.demo.api.management.input.EmployeeForm;
import com.jdc.spring.demo.api.management.input.EmployeeSearch;
import com.jdc.spring.demo.api.management.output.EmployeeDetails;
import com.jdc.spring.demo.api.management.output.EmployeeListItem;
import com.jdc.spring.demo.model.ModificationResult;
import com.jdc.spring.demo.model.entity.Employee;
import com.jdc.spring.demo.model.entity.Employee_;
import com.jdc.spring.demo.model.repo.EmployeeRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {
	
	private final EmployeeRepo repo;

	public List<EmployeeListItem> search(EmployeeSearch search) {
		return repo.search(cb -> {
			var cq = cb.createQuery(EmployeeListItem.class);
			var root = cq.from(Employee.class);
			
			EmployeeListItem.select(cb, cq, root);
			cq.where(search.where(cb, root));
			
			cq.orderBy(cb.desc(root.get(Employee_.createdAt)));
			
			return cq;
		});
	}

	public EmployeeDetails findById(int id) {
		return safeCall(repo.findById(id).map(EmployeeDetails::from))
				.apply("employee").apply("id").apply(id);
	}

	public ModificationResult<Integer> create(EmployeeForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	public ModificationResult<Integer> update(int id, EmployeeForm form) {
		// TODO Auto-generated method stub
		return null;
	}

}
