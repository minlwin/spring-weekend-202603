package com.jdc.product.api;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.product.api.input.ProductForm;
import com.jdc.product.api.input.ProductSearch;
import com.jdc.product.api.output.ModificationResult;
import com.jdc.product.api.output.ProductDetails;
import com.jdc.product.api.output.ProductListItem;
import com.jdc.product.model.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductApi {

	private final ProductService service;
	
	@GetMapping
	List<ProductListItem> search(ProductSearch form) {
		return service.search(form);
	}
	
	@GetMapping("{id}")
	ProductDetails findById(@PathVariable int id) {
		return service.findById(id);
	}
	
	@PostMapping
	ModificationResult<Integer> create(@Validated @RequestBody ProductForm form) {
		return service.create(form);
	}
	
	@PutMapping("{id}")
	ModificationResult<Integer> update(@PathVariable int id, @Validated @RequestBody ProductForm form) {
		return service.update(id, form);
	}	
}
