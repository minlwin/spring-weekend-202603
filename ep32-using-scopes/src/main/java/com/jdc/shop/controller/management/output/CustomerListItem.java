package com.jdc.shop.controller.management.output;

import java.time.LocalDateTime;
import java.util.UUID;

import com.jdc.shop.model.entity.Account_;
import com.jdc.shop.model.entity.Customer;
import com.jdc.shop.model.entity.Customer_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerListItem {

	private UUID id;
	private String name;
	private String email;
	private String phone;
	private LocalDateTime entryAt;
	private long invoiceCount;
	
	public static void select(CriteriaQuery<CustomerListItem> cq, CriteriaBuilder cb, Root<Customer> root) {
		
		var invoice = root.join(Customer_.invoices, JoinType.LEFT);
		
		cq.select(
			cb.construct(CustomerListItem.class, 
			root.get(Customer_.id),
			root.get(Customer_.name),
			root.get(Customer_.account).get(Account_.email),
			root.get(Customer_.phone),
			root.get(Customer_.entryAt),
			cb.count(invoice)
		));
		
		cq.groupBy(
			root.get(Customer_.id),
			root.get(Customer_.name),
			root.get(Customer_.account).get(Account_.email),
			root.get(Customer_.phone),
			root.get(Customer_.entryAt)
		);
	}
}
