package com.jdc.shop.controller.management.output;

import java.time.LocalDateTime;
import java.util.UUID;

import com.jdc.shop.model.entity.Account_;
import com.jdc.shop.model.entity.Customer_;
import com.jdc.shop.model.entity.Invoice;
import com.jdc.shop.model.entity.Invoice_;
import com.jdc.shop.model.entity.InvoiceItem_;
import com.jdc.shop.model.entity.Invoice.Status;

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
public class InvoiceListItem {
	private UUID id;
	private UUID customerId;
	private String customerName;
	private String customerEmail;
	private String customerPhone;
	private LocalDateTime invoiceAt;
	private Status status;
	private long totalItems;
	private int totalAmount;

	public static void select(CriteriaQuery<InvoiceListItem> cq, CriteriaBuilder cb, Root<Invoice> root) {
		var items = root.join(Invoice_.items, JoinType.LEFT);

		cq.select(cb.construct(InvoiceListItem.class,
			root.get(Invoice_.id),
			root.get(Invoice_.customer).get(Customer_.id),
			root.get(Invoice_.customer).get(Customer_.name),
			root.get(Invoice_.customer).get(Customer_.account).get(Account_.email),
			root.get(Invoice_.customer).get(Customer_.phone),
			root.get(Invoice_.invoiceAt),
			root.get(Invoice_.status),
			cb.count(items),
			cb.coalesce(cb.sum(cb.prod(items.get(InvoiceItem_.unitPrice), items.get(InvoiceItem_.quantity))), 0)
		));

		cq.groupBy(
			root.get(Invoice_.id),
			root.get(Invoice_.customer).get(Customer_.id),
			root.get(Invoice_.customer).get(Customer_.name),
			root.get(Invoice_.customer).get(Customer_.account).get(Account_.email),
			root.get(Invoice_.customer).get(Customer_.phone),
			root.get(Invoice_.invoiceAt),
			root.get(Invoice_.status)
		);
	}
}
