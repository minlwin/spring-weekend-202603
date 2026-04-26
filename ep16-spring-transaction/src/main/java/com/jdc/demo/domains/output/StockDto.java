package com.jdc.demo.domains.output;

public record StockDto(
	int productId,
	int version,
	int stockAmount,
	int purchasePrice,
	int salePrice
	) {

}
