package com.jdc.shop.model.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jdc.shop.controller.anonymous.output.ProductDto;
import com.jdc.shop.model.PageResult;

@Service
public class ProductService {

	public PageResult<ProductDto> search(String category, String keyword, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<ProductDto> findById(UUID product) {
		
		return Optional.empty();
	}

}
