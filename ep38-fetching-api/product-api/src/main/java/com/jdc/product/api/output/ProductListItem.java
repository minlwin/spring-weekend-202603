package com.jdc.product.api.output;

import com.jdc.product.model.Status;

public record ProductListItem(
		int id,
		String category,
		String name,
		int unitPrice,
		Status status) {

}
