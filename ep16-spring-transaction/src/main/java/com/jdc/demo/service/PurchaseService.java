package com.jdc.demo.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jdc.demo.domains.AppBusinessException;
import com.jdc.demo.domains.input.PurchaseForm;
import com.jdc.demo.domains.output.PurchaseDetails;
import com.jdc.demo.domains.output.StockHistoryDto;
import com.jdc.demo.repo.EmployeeRepo;
import com.jdc.demo.repo.ProductRepo;
import com.jdc.demo.repo.ProductSupplierRepo;
import com.jdc.demo.repo.PurchaseProductRepo;
import com.jdc.demo.repo.PurchaseRepo;
import com.jdc.demo.repo.StockHistoryRepo;
import com.jdc.demo.repo.StockRepo;
import com.jdc.demo.repo.SupplierRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseService {
	
	private static final String ACTION = "Purchase";
	
	private final EmployeeRepo employeeRepo;
	private final ProductRepo productRepo;
	private final SupplierRepo supplierRepo;
	private final PurchaseRepo purchaseRepo;
	private final StockRepo stockRepo;
	private final ProductSupplierRepo productSupplierRepo;
	private final PurchaseProductRepo purchaseProductRepo;
	private final StockHistoryRepo stockHistoryRepo;
	
	@Value("${app.sale-price-rate}")
	private int salePriceRate;

	public int purchase(PurchaseForm form) {
		
		validate(form);
		
		var id = purchaseRepo.create(form);
		
		for(var item : form.items()) {
			
			productSupplierRepo.createIfNeed(item.productId(), form.supplierId());
			
			var stock = stockRepo.findById(item.productId());
			
			// Calculate Sale Price
			var purchasePrice = item.unitPrice();
			var stockPurchasePrice = stock.purchasePrice();	
			var salePrice = getSalePrice(purchasePrice > stockPurchasePrice ? purchasePrice : stockPurchasePrice);
			
			purchaseProductRepo.create(id, stock.version(), item);
			
			var stockHistory = StockHistoryDto.builder()
					.productId(item.productId())
					.version(stock.version() + 1)
					.actionType(ACTION)
					.purchasePrice(purchasePrice)
					.salePrice(salePrice)
					.amount(stock.stockAmount() + item.quantity())
					.build();
			
			stockHistoryRepo.create(stockHistory);
			
			stockRepo.update(stockHistory);
		}
		
		return id;
	}
	
	private int getSalePrice(int purchasePrice) {
		var profit = purchasePrice / 100 * salePriceRate;
		return purchasePrice + profit;
	}

	public Optional<PurchaseDetails> findById(int id) {
		var header = purchaseRepo.findById(id);
		return header.map(a -> new PurchaseDetails(a, purchaseProductRepo.findByPurchaseId(id)));
	}
	
	private void validate(PurchaseForm form) {
		if(null == form.purchaseDate()) {
			throw new AppBusinessException("Please enter purchase date.");
		}
		
		if(form.purchaseDate().compareTo(LocalDate.now()) > 0) {
			throw new AppBusinessException("Purchase date should not be a future date.");
		}
		
		if(null == form.items() 
				|| form.items().isEmpty()) {
			throw new AppBusinessException("There is no purchase item.");
		}
		
		if(form.transportationFees() < 0) {
			throw new AppBusinessException("Transportation Fee should be positive number.");
		}
		
		for(var item : form.items()) {
			if(item.quantity() <= 0) {
				throw new AppBusinessException("Quantity should be greater than zero.");
			}
			
			if(item.unitPrice() < 0) {
				throw new AppBusinessException("Purchase price should be positive number.");
			}
		}
		
		// Checks for Foreign Keys
		// Employee ID
		if(!employeeRepo.isPresent(form.employeeId())) {
			throw new AppBusinessException("There is no employee with id %s.".formatted(form.employeeId()));
		}
		
		// Supplier ID
		if(!supplierRepo.isPresent(form.supplierId())) {
			throw new AppBusinessException("There is no supplier with id %s.".formatted(form.supplierId()));
		}
		
		// Product ID
		for(var item : form.items()) {
			if(!productRepo.isPresent(item.productId())) {
				throw new AppBusinessException("There is no product with id %s.".formatted(item.productId()));
			}
		}
		
	}	
}
