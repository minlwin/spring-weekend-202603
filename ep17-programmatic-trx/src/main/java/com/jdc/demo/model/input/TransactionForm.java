package com.jdc.demo.model.input;

import java.time.LocalDateTime;

public record TransactionForm(
		String trxId,
		String trxType,
		String issuer,
		int amount,
		String remark,
		LocalDateTime issueAt) {
	
	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String trxId;
		private String trxType;
		private String issuer;
		private int amount;
		private String remark;
		private LocalDateTime issueAt;

		public Builder trxId(String trxId) {
			this.trxId = trxId;
			return this;
		}

		public Builder trxType(String trxType) {
			this.trxType = trxType;
			return this;
		}

		public Builder issuer(String issuer) {
			this.issuer = issuer;
			return this;
		}

		public Builder amount(int amount) {
			this.amount = amount;
			return this;
		}

		public Builder remark(String remark) {
			this.remark = remark;
			return this;
		}

		public Builder issueAt(LocalDateTime issueAt) {
			this.issueAt = issueAt;
			return this;
		}

		public TransactionForm build() {
			return new TransactionForm(trxId, trxType, issuer, amount, remark, issueAt);
		}
	}
}

