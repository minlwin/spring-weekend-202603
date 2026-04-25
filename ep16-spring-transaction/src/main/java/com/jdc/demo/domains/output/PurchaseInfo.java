package com.jdc.demo.domains.output;

import java.time.LocalDate;

public record PurchaseInfo(
        int id,
        int employeeId,
        String employeeName,
        int supplierId,
        String supplierName,
        String supplierPhone,
        String supplierEmail,
        LocalDate purchaseDate,
        int transportationFee) {

    public static class Builder {
        private int id;
        private int employeeId;
        private String employeeName;
        private int supplierId;
        private String supplierName;
        private String supplierPhone;
        private String supplierEmail;
        private LocalDate purchaseDate;
        private int transportationFee;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder employeeId(int employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public Builder employeeName(String employeeName) {
            this.employeeName = employeeName;
            return this;
        }

        public Builder supplierId(int supplierId) {
            this.supplierId = supplierId;
            return this;
        }

        public Builder supplierName(String supplierName) {
            this.supplierName = supplierName;
            return this;
        }

        public Builder supplierPhone(String supplierPhone) {
            this.supplierPhone = supplierPhone;
            return this;
        }

        public Builder supplierEmail(String supplierEmail) {
            this.supplierEmail = supplierEmail;
            return this;
        }

        public Builder purchaseDate(LocalDate purchaseDate) {
            this.purchaseDate = purchaseDate;
            return this;
        }

        public Builder transportationFee(int transportationFee) {
            this.transportationFee = transportationFee;
            return this;
        }

        public PurchaseInfo build() {
            return new PurchaseInfo(
                    id,
                    employeeId,
                    employeeName,
                    supplierId,
                    supplierName,
                    supplierPhone,
                    supplierEmail,
                    purchaseDate,
                    transportationFee
            );
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
