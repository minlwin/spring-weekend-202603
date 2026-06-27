package com.jdc.shop.model.service;

import java.time.LocalDateTime;
import java.util.function.Function;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.shop.controller.anonymous.input.SignUpForm;
import com.jdc.shop.controller.management.input.CustomerSearch;
import com.jdc.shop.controller.management.output.CustomerListItem;
import com.jdc.shop.model.PageResult;
import com.jdc.shop.model.entity.Account;
import com.jdc.shop.model.entity.Account.Role;
import com.jdc.shop.model.entity.Customer;
import com.jdc.shop.model.entity.Customer_;
import com.jdc.shop.model.repository.AccountRepo;
import com.jdc.shop.model.repository.CustomerRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
	
	private final CustomerRepo repo;
	private final AccountRepo accountRepo;
	private final PasswordEncoder passwordEncoder;

	@Transactional(readOnly = true)
	public PageResult<CustomerListItem> search(CustomerSearch form, int page, int size) {
		return repo.search(queryFunc(form), countFunc(form), page, size);
	}

	private Function<CriteriaBuilder, CriteriaQuery<CustomerListItem>> queryFunc(CustomerSearch form) {
		return cb -> {
			var cq = cb.createQuery(CustomerListItem.class);
			var root = cq.from(Customer.class);

			CustomerListItem.select(cq, cb, root);
			cq.where(form.where(cb, root));
			cq.orderBy(cb.desc(root.get(Customer_.entryAt)));

			return cq;
		};
	}

	private Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc(CustomerSearch form) {
		return cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(Customer.class);

			cq.select(cb.count(root));
			cq.where(form.where(cb, root));

			return cq;
		};
	}

	@Transactional
	public void create(SignUpForm form) {
		var email = form.getEmail().trim();
		if (accountRepo.findOneByEmail(email).isPresent()) {
			throw new IllegalArgumentException("Email is already registered.");
		}

		var account = new Account();
		account.setEmail(email);
		account.setPassword(passwordEncoder.encode(form.getPassword()));
		account.setRole(Role.Customer);

		var entity = new Customer();
		entity.setAccount(account);
		entity.setName(form.getName().trim());
		entity.setEntryAt(LocalDateTime.now());

		repo.save(entity);
	}
}
