package com.jdc.demo.test;

import org.junit.jupiter.api.Test;

import jakarta.persistence.Persistence;

public class OrmTest {

	@Test
	void test() {
		var emf = Persistence.createEntityManagerFactory("mapping-jpa");
		emf.close();
	}
}
