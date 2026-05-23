package com.jdc.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.demo.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{

}
