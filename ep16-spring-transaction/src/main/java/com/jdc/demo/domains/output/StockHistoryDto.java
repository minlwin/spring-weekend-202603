package com.jdc.demo.domains.output;

public record StockHistoryDto(
        int productId,
        int version,
        String actionType,
        int purchasePrice,
        int salePrice,
        int amount) {

    public static class Builder {
        private int productId;
        private int version;
        private String actionType;
        private int purchasePrice;
        private int salePrice;
        private int amount;

        public Builder productId(int productId) {
            this.productId = productId;
            return this;
        }

        public Builder version(int version) {
            this.version = version;
            return this;
        }

        public Builder actionType(String actionType) {
            this.actionType = actionType;
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

        public Builder amount(int amount) {
            this.amount = amount;
            return this;
        }

        public StockHistoryDto build() {
            return new StockHistoryDto(
                    productId,
                    version,
                    actionType,
                    purchasePrice,
                    salePrice,
                    amount
            );
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
