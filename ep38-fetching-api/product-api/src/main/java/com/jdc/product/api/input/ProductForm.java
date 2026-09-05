package com.jdc.product.api.input;

import com.jdc.product.model.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductForm(
		@NotBlank(message = "Please enter product name.")
		String name,
		@NotBlank(message = "Please enter category.")
		String category,
		@Positive(message = "Price must be positive number.")
		@NotNull(message = "Please enter unit price.")
		Integer unitPrice,
		@NotNull(message = "Please select status.")
		Status status,
		String description) {

}
