package com.jdc.demo.domains.output;

import java.time.LocalDateTime;

public record SaleInfo(
		int id,
		int customerId,
		String customerName,
		LocalDateTime saleAt,
		int deliveryFee,
		int discount, 
		double taxRate) {

	public static class Builder {

		private int id;
		private int customerId;
		private String customerName;
		private LocalDateTime saleAt;
		private int deliveryFee;
		private int discount;
		private double taxRate;

		public Builder id(int id) {
			this.id = id;
			return this;
		}

		public Builder customerId(int customerId) {
			this.customerId = customerId;
			return this;
		}

		public Builder customerName(String customerName) {
			this.customerName = customerName;
			return this;
		}

		public Builder saleAt(LocalDateTime saleAt) {
			this.saleAt = saleAt;
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

		public Builder taxRate(double taxRate) {
			this.taxRate = taxRate;
			return this;
		}

		public SaleInfo build() {
			return new SaleInfo(
				id,
				customerId,
				customerName,
				saleAt,
				deliveryFee,
				discount,
				taxRate
			);
		}
	}
	
	public static Builder builder() {
		return new Builder();
	}
}
