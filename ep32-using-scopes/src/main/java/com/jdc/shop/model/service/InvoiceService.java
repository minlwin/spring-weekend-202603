package com.jdc.shop.model.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.shop.controller.anonymous.output.ShoppingCart;
import com.jdc.shop.controller.management.input.InvoiceSearch;
import com.jdc.shop.controller.management.output.InvoiceDetails;
import com.jdc.shop.controller.management.output.InvoiceListItem;
import com.jdc.shop.model.PageResult;
import com.jdc.shop.model.entity.Account_;
import com.jdc.shop.model.entity.Customer_;
import com.jdc.shop.model.entity.Invoice;
import com.jdc.shop.model.entity.Invoice.Status;
import com.jdc.shop.model.entity.InvoiceItem;
import com.jdc.shop.model.entity.InvoiceItemPk;
import com.jdc.shop.model.entity.Invoice_;
import com.jdc.shop.model.repository.CustomerRepo;
import com.jdc.shop.model.repository.InvoiceRepo;
import com.jdc.shop.model.repository.ProductRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InvoiceService {

	private final InvoiceRepo repo;
	private final CustomerRepo customerRepo;
	private final ProductRepo productRepo;
	
	@Value("${app.conf.max-address}")
	private int maxAddress;

	@PreAuthorize("isAuthenticated()")
	public PageResult<InvoiceListItem> search(InvoiceSearch form, int page, int size) {
		return repo.search(queryFunc(form), countFunc(form), page, size);
	}

	@PreAuthorize("isAuthenticated()")
	public InvoiceDetails findById(UUID id) {
		return repo.findById(id)
			.map(InvoiceDetails::from)
			.orElse(null);
	}

	@Transactional
	@PreAuthorize("hasAuthority('Customer')")
	public UUID checkOut(ShoppingCart cart) {
		var email = SecurityContextHolder.getContext().getAuthentication().getName();
		var customer = customerRepo.findByAccountEmail(email).orElseThrow();

		var invoice = new Invoice();
		invoice.setCustomer(customer);
		invoice.setInvoiceAt(LocalDateTime.now());
		invoice.setStatus(Status.Invoiced);
		invoice.setAddress(cart.getAddress());
		
		invoice = repo.save(invoice);
		
		var cartItems = cart.getItems();

		for (int i = 0; i < cartItems.size(); i++) {
			var cartItem = cartItems.get(i);

			var pk = new InvoiceItemPk();
			pk.setSeqNumber(i + 1);

			var item = new InvoiceItem();
			item.setId(pk);
			item.setProduct(productRepo.getReferenceById(cartItem.getId()));
			
			item.setUnitPrice(cartItem.getUnitPrice());
			item.setQuantity(cartItem.getQuantity());
			
			invoice.addItem(item);
		}

		return invoice.getId();
	}
	
	
	public List<String> findAddresses(String username) {
		
		Function<CriteriaBuilder, CriteriaQuery<String>> queryFunc = cb -> {
			var cq = cb.createQuery(String.class);

			var root = cq.from(Invoice.class);
			cq.select(root.get(Invoice_.address));

			cq.where(cb.equal(root.get(Invoice_.customer).get(Customer_.account).get(Account_.email), username));
			cq.groupBy(root.get(Invoice_.address));
			cq.orderBy(cb.desc(cb.greatest(root.get(Invoice_.invoiceAt))));

			return cq;
		};
		
		return repo.search(queryFunc, maxAddress);
	}

	private Function<CriteriaBuilder, CriteriaQuery<InvoiceListItem>> queryFunc(InvoiceSearch form) {
		return cb -> {
			var cq = cb.createQuery(InvoiceListItem.class);
			var root = cq.from(Invoice.class);
			InvoiceListItem.select(cq, cb, root);
			cq.where(form.where(cb, root));
			cq.orderBy(cb.desc(root.get(Invoice_.invoiceAt)));
			return cq;
		};
	}

	private Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc(InvoiceSearch form) {
		return cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(Invoice.class);
			cq.select(cb.count(root));
			cq.where(form.where(cb, root));
			return cq;
		};
	}

}
