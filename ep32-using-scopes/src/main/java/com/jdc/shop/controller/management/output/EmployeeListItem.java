package com.jdc.shop.controller.management.output;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.jdc.shop.model.entity.Account_;
import com.jdc.shop.model.entity.Employee;
import com.jdc.shop.model.entity.Employee_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeListItem {

	private UUID id;
	private String name;
	private String phone;
	private String email;
	private LocalDateTime entryAt;
	private LocalDate retiredAt;
	
	public static void select(CriteriaQuery<EmployeeListItem> cq, CriteriaBuilder cb, Root<Employee> root) {
		cq.select(
			cb.construct(EmployeeListItem.class, 
			root.get(Employee_.id),
			root.get(Employee_.name),
			root.get(Employee_.phone),
			root.get(Employee_.account).get(Account_.email),
			root.get(Employee_.entryAt),
			root.get(Employee_.retiredAt)
		));
	}
}
