package com.jdc.demo.test;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.jdc.demo.entity.Product;
import com.jdc.demo.entity.Product.Status;
import com.jdc.demo.entity.Property;

import jakarta.persistence.Persistence;

public class OrmTest {

	@Test
	void test() {
		var emf = Persistence.createEntityManagerFactory("mapping-jpa");
			
		var em = emf.createEntityManager();
		em.getTransaction().begin();
		
		var product = new Product();
		product.setName("Banana");
		product.setUnitPrice(3000);
		product.setStatus(Status.Available);
		product.setTags(Set.of("Fruits"));
		product.setProperties(List.of(Property.get("size", "M")));
		
		em.persist(product);
		em.getTransaction().commit();
		emf.close();
	}
}
