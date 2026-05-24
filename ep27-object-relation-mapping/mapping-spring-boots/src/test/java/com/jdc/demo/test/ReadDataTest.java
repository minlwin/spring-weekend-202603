package com.jdc.demo.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jdc.demo.entity.Product;

import jakarta.persistence.EntityManager;

@SpringBootTest
public class ReadDataTest {

	@Autowired
	private EntityManager em;
	
	@Test
	void test() {
		var product = em.find(Product.class, 1);
		System.out.println(product);
	}
}
