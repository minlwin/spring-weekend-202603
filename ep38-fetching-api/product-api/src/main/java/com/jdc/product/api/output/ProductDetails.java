package com.jdc.product.api.output;

import java.time.LocalDateTime;

import com.jdc.product.model.Status;
import com.jdc.product.model.entity.Product;

public record ProductDetails(		
		int id,
		String category,
		String name,
		int unitPrice,
		Status status,
		String description,
		LocalDateTime createdAt,
		LocalDateTime modifiedAt) {

	public static ProductDetails from(Product entity) {
		return new ProductDetails(
				entity.getId(), 
				entity.getName(), 
				entity.getCategory(), 
				entity.getUnitPrice(), 
				entity.getStatus(), 
				entity.getDescription(), 
				entity.getCreatedAt(), 
				entity.getModifiedAt());
	}

}
