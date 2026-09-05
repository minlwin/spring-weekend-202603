package com.jdc.product.api.input;

import com.jdc.product.model.Status;

public record ProductSearch(
		Status status,
		String keyword) {

}
