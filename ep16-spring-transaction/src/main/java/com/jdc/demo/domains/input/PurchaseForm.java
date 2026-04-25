package com.jdc.demo.domains.input;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record PurchaseForm(
        int supplierId,
        int employeeId,
        LocalDate purchaseDate,
        int transportationFees,
        List<PurchaseFormItem> items) {

    public static class Builder {
        private int supplierId;
        private int employeeId;
        private LocalDate purchaseDate;
        private int transportationFees;
        private List<PurchaseFormItem> items = new ArrayList<>();

        public Builder supplierId(int supplierId) {
            this.supplierId = supplierId;
            return this;
        }

        public Builder employeeId(int employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public Builder purchaseDate(LocalDate purchaseDate) {
            this.purchaseDate = purchaseDate;
            return this;
        }

        public Builder transportationFees(int transportationFees) {
            this.transportationFees = transportationFees;
            return this;
        }

        public Builder items(List<PurchaseFormItem> items) {
            this.items = items;
            return this;
        }

        public Builder addItem(PurchaseFormItem item) {
            this.items.add(item);
            return this;
        }

        public PurchaseForm build() {
            return new PurchaseForm(supplierId, employeeId, purchaseDate, transportationFees, items);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}

