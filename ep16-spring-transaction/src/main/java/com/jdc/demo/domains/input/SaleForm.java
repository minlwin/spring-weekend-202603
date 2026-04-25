package com.jdc.demo.domains.input;

import java.util.ArrayList;
import java.util.List;

public record SaleForm(
	int customerId,
	int discount,
	int deliveryFee,
	List<SaleFormItem> items
) {

	public static class Builder {
		private int customerId;
		private int discount;
		private int deliveryFee;
		private List<SaleFormItem> items = new ArrayList<SaleFormItem>();
		
		public SaleForm build() {
			return new SaleForm(customerId, discount, deliveryFee, items);
		}
		
		public Builder customerId(int customerId) {
			this.customerId = customerId;
			return this;
		}
		
		public Builder deliveryFee(int deliveryFee) {
			this.deliveryFee = deliveryFee;
			return this;
		}
		
		public Builder discount(int discount) {
			this.discount = discount;
			return this;
		}

		public Builder addItem(int productId, int quantity) {
			this.items.add(new SaleFormItem(productId, quantity));
			return this;
		}
	}
	
	public static Builder builder() {
		return new Builder();
	}
}
