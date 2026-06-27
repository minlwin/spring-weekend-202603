package com.jdc.shop.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jdc.shop.controller.anonymous.output.CategoryDto;
import com.jdc.shop.model.repository.CategoryRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepo repo;

	public List<CategoryDto> findAll() {
		return repo.searchDtoList();
	}
}
