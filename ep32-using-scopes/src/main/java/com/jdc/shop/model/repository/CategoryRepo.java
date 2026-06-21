package com.jdc.shop.model.repository;

import java.util.Optional;

import com.jdc.shop.model.BaseRepository;
import com.jdc.shop.model.entity.Category;

public interface CategoryRepo extends BaseRepository<Category, Integer>{

	Optional<Category> findByName(String name);
}
