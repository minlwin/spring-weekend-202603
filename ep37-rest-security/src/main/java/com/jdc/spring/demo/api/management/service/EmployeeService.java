package com.jdc.spring.demo.api.management.service;

import static com.jdc.spring.demo.utils.OptionalsUtils.safeCall;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.spring.demo.api.management.input.EmployeeForm;
import com.jdc.spring.demo.api.management.input.EmployeeSearch;
import com.jdc.spring.demo.api.management.output.EmployeeDetails;
import com.jdc.spring.demo.api.management.output.EmployeeListItem;
import com.jdc.spring.demo.model.ModificationResult;
import com.jdc.spring.demo.model.entity.Employee;
import com.jdc.spring.demo.model.entity.Employee_;
import com.jdc.spring.demo.model.entity.VerificationHistory.Action;
import com.jdc.spring.demo.model.repo.AccountRepo;
import com.jdc.spring.demo.model.repo.EmployeeRepo;
import com.jdc.spring.demo.model.service.AccountVerificationService;
import com.jdc.spring.demo.utils.exceptions.BusinessRuleViolationException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {
	
	private final EmployeeRepo employeeRepo;
	private final AccountRepo accountRepo;
	private final AccountVerificationService verificationService;

	@Transactional(readOnly = true)
	public List<EmployeeListItem> search(EmployeeSearch search) {
		return employeeRepo.search(cb -> {
			var cq = cb.createQuery(EmployeeListItem.class);
			var root = cq.from(Employee.class);
			
			EmployeeListItem.select(cb, cq, root);
			cq.where(search.where(cb, root));
			
			cq.orderBy(cb.desc(root.get(Employee_.createdAt)));
			
			return cq;
		});
	}

	@Transactional(readOnly = true)
	public EmployeeDetails findById(int id) {
		return safeCall(employeeRepo.findById(id).map(EmployeeDetails::from))
				.apply("employee").apply("id").apply(id);
	}

	@Transactional
	public ModificationResult<Integer> create(EmployeeForm form) {
		
		// Check Email
		if(accountRepo.findOneByEmail(form.email()).isPresent()) {
			throw new BusinessRuleViolationException("%s is already used in other account. Please check email.".formatted(form.email()));
		}
		
		// Create Account
		var account = accountRepo.save(form.account());
		
		// Create Employee
		var employee = new Employee();
		employee.setAccount(account);
		employee.setPhone(form.phone());
		
		employee = employeeRepo.save(employee);
		
		// Send Verification Code
		verificationService.sendVerification(account, Action.ActivateEmployee);
		
		return null;
	}

	@Transactional
	public ModificationResult<Integer> update(int id, EmployeeForm form) {
		var entity = safeCall(employeeRepo.findById(id))
				.apply("employee").apply("id").apply(id);

		if(!entity.getAccount().getEmail().equals(form.email())) {
			if(accountRepo.findOneByEmail(form.email()).isPresent()) {
				throw new BusinessRuleViolationException("%s is already used in other account. Please check email.".formatted(form.email()));
			}
			entity.getAccount().setEmail(form.email());
		}
		
		entity.getAccount().setName(form.name());
		entity.setPhone(form.phone());
		
		return new ModificationResult<>(entity.getId());
	}

}
