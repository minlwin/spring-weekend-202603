package com.jdc.demo.domains.output;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record SaleDetails(
		SaleInfo header,
		List<SaleItem> items) {

	public static class Builder {
		private SaleInfo.Builder headerBuidler = SaleInfo.builder();
		private List<SaleItem> items = new ArrayList<>();
		
		public SaleDetails build() {
			return new SaleDetails(headerBuidler.build(), items);
		}
		
		public Builder id(int id) {
			this.headerBuidler.id(id);
			return this;
		}

		public Builder customerId(int customerId) {
			this.headerBuidler.customerId(customerId);
			return this;
		}

		public Builder customerName(String customerName) {
			this.headerBuidler.customerName(customerName);
			return this;
		}

		public Builder saleAt(LocalDateTime saleAt) {
			this.headerBuidler.saleAt(saleAt);
			return this;
		}

		public Builder deliveryFee(int deliveryFee) {
			this.headerBuidler.deliveryFee(deliveryFee);
			return this;
		}

		public Builder discount(int discount) {
			this.headerBuidler.discount(discount);
			return this;
		}

		public Builder taxRate(double taxRate) {
			this.headerBuidler.taxRate(taxRate);
			return this;
		}
		
		public Builder addItem(SaleItem item) {
			this.items.add(item);
			return this;
		}
		
	}
	
	public static Builder builder() {
		return new Builder();
	}
}
