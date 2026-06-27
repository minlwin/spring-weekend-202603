package com.jdc.shop.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.jdc.shop.controller.anonymous.output.CategoryDto;
import com.jdc.shop.model.BaseRepository;
import com.jdc.shop.model.entity.Category;

public interface CategoryRepo extends BaseRepository<Category, Integer>{

	Optional<Category> findByName(String name);
	
	@Query("select new com.jdc.shop.controller.anonymous.output.CategoryDto(c.id, c.name) from Category c order by c.name")
	List<CategoryDto> searchDtoList();
}
