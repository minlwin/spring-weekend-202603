package com.jdc.demo.test;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionTemplate;

import com.jdc.demo.entity.Product;
import com.jdc.demo.entity.Product.Status;
import com.jdc.demo.entity.Property;

import jakarta.persistence.EntityManager;

@SpringBootTest
public class LoadContextTest {
	
	@Autowired
	private EntityManager em;
	@Autowired
	private TransactionTemplate trx;

	@Test
	void test() {
		
		trx.executeWithoutResult(_ -> {
			var product = new Product();
			product.setName("Banana");
			product.setUnitPrice(3000);
			product.setStatus(Status.Available);
			product.setTags(Set.of("Fruits"));
			product.setProperties(List.of(Property.get("size", "M")));
			
			em.persist(product);
		});
	}
}
