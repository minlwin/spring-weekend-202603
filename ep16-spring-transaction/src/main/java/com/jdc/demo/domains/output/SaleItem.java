package com.jdc.demo.domains.output;

public record SaleItem(
		int productId,
		String productName,
		int salePrice,
		int quantity,
		int remains) {

	public static class Builder {

		private int productId;
		private String productName;
		private int salePrice;
		private int quantity;
		private int remains;

		public Builder productId(int productId) {
			this.productId = productId;
			return this;
		}

		public Builder productName(String productName) {
			this.productName = productName;
			return this;
		}

		public Builder salePrice(int salePrice) {
			this.salePrice = salePrice;
			return this;
		}

		public Builder quantity(int quantity) {
			this.quantity = quantity;
			return this;
		}

		public Builder remains(int remains) {
			this.remains = remains;
			return this;
		}

		public SaleItem build() {
			return new SaleItem(
				productId,
				productName,
				salePrice,
				quantity,
				remains
			);
		}
	}
	
	public static Builder builder() {
		return new Builder();
	}
}
