package com.jdc.demo.domains.output;

public record PurchaseItem(
        int productId,
        int version,
        String productName,
        int amount,
        int purchasePrice,
        int salePrice,
        int stock,
        int lastPurchasePrice,
        int lastSalePrice,
        int lastStock) {

    public static class Builder {
        private int productId;
        private int version;
        private String productName;
        private int amount;
        private int purchasePrice;
        private int salePrice;
        private int stock;
        private int lastPurchasePrice;
        private int lastSalePrice;
        private int lastStock;

        public Builder productId(int productId) {
            this.productId = productId;
            return this;
        }

        public Builder version(int version) {
            this.version = version;
            return this;
        }

        public Builder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public Builder amount(int amount) {
            this.amount = amount;
            return this;
        }

        public Builder purchasePrice(int purchasePrice) {
            this.purchasePrice = purchasePrice;
            return this;
        }

        public Builder salePrice(int salePrice) {
            this.salePrice = salePrice;
            return this;
        }

        public Builder stock(int stock) {
            this.stock = stock;
            return this;
        }

        public Builder lastPurchasePrice(int lastPurchasePrice) {
            this.lastPurchasePrice = lastPurchasePrice;
            return this;
        }

        public Builder lastSalePrice(int lastSalePrice) {
            this.lastSalePrice = lastSalePrice;
            return this;
        }

        public Builder lastStock(int lastStock) {
            this.lastStock = lastStock;
            return this;
        }

        public PurchaseItem build() {
            return new PurchaseItem(
                    productId,
                    version,
                    productName,
                    amount,
                    purchasePrice,
                    salePrice,
                    stock,
                    lastPurchasePrice,
                    lastSalePrice,
                    lastStock
            );
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
