package com.jdc.shop.model.service;

import java.time.LocalDateTime;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.shop.controller.management.input.EmployeeForm;
import com.jdc.shop.controller.management.input.EmployeeSearch;
import com.jdc.shop.controller.management.output.EmployeeListItem;
import com.jdc.shop.model.PageResult;
import com.jdc.shop.model.entity.Account;
import com.jdc.shop.model.entity.Account.Role;
import com.jdc.shop.model.entity.Employee;
import com.jdc.shop.model.entity.Employee_;
import com.jdc.shop.model.repository.EmployeeRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepo repo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public void create(EmployeeForm form) {
		
		var account = new Account();
		account.setEmail(form.getEmail());
		account.setRole(Role.Employee);
		account.setPassword(passwordEncoder.encode(form.getPhone()));
		
		var entity = new Employee();
		entity.setAccount(account);
		entity.setName(form.getName());
		entity.setPhone(form.getPhone());
		entity.setEntryAt(LocalDateTime.now());
		
		repo.save(entity);
	}

	@Transactional(readOnly = true)
	public PageResult<EmployeeListItem> search(EmployeeSearch form, int page, int size) {
		return repo.search(queryFunc(form), countFunc(form), page, size);
	}

	private Function<CriteriaBuilder, CriteriaQuery<EmployeeListItem>> queryFunc(EmployeeSearch form) {
		return cb -> {
			var cq = cb.createQuery(EmployeeListItem.class);
			var root = cq.from(Employee.class);
			
			EmployeeListItem.select(cq, cb, root);
			cq.where(form.where(cb, root));
			
			cq.orderBy(cb.desc(root.get(Employee_.entryAt)));
			
			return cq;
		};
	}

	private Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc(EmployeeSearch form) {
		return cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(Employee.class);
			
			cq.select(cb.count(root));
			cq.where(form.where(cb, root));
			
			return cq;
		};
	}

}
