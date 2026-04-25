package com.jdc.demo.domains.output;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record PurchaseDetails(
		PurchaseInfo header,
		List<PurchaseItem> items) {
	
	public static class Builder {
		
		private PurchaseInfo.Builder headerBuilder = PurchaseInfo.builder();
		private List<PurchaseItem> items = new ArrayList<>();
		
		
        public Builder id(int id) {
            this.headerBuilder.id(id);
            return this;
        }

        public Builder employeeId(int employeeId) {
            this.headerBuilder.employeeId(employeeId);
            return this;
        }

        public Builder employeeName(String employeeName) {
            this.headerBuilder.employeeName(employeeName);
            return this;
        }

        public Builder supplierId(int supplierId) {
            this.headerBuilder.supplierId(supplierId);
            return this;
        }

        public Builder supplierName(String supplierName) {
            this.headerBuilder.supplierName(supplierName);
            return this;
        }

        public Builder supplierPhone(String supplierPhone) {
            this.headerBuilder.supplierPhone(supplierPhone);
            return this;
        }

        public Builder supplierEmail(String supplierEmail) {
            this.headerBuilder.supplierEmail(supplierEmail);
            return this;
        }

        public Builder purchaseDate(LocalDate purchaseDate) {
            this.headerBuilder.purchaseDate(purchaseDate);
            return this;
        }

        public Builder transportationFee(int transportationFee) {
            this.headerBuilder.transportationFee(transportationFee);
            return this;
        }
        
        public Builder addItem(PurchaseItem item) {
        	this.items.add(item);
        	return this;
        }
        
		public PurchaseDetails build() {
			return new PurchaseDetails(headerBuilder.build(), items);
		}
	}
	
	public static Builder builder() {
		return new Builder();
	}

	public int getSubTotal() {
		return items.stream()
				.mapToInt(a -> a.purchasePrice() * a.amount()).sum();
	}
	
	public int getAllTotal() {
		return getSubTotal() + header.transportationFee();
	}
}
