package com.jdc.demo.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.jdc.demo.entity.Auditable;
import com.jdc.demo.entity.Student;
import com.jdc.demo.entity.Student.Education;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@TestMethodOrder(value = OrderAnnotation.class)
public class LifecycleTest {
	
	private static EntityManagerFactory emf;
	
	@BeforeAll
	public static void prepare() {
		emf = Persistence.createEntityManagerFactory("entity-lifecycle");
	}
	
	@AfterAll
	public static void close() {
		emf.close();
	}
	
	private EntityManager em;
	
	@BeforeEach
	public void beforeEach() {
		em = emf.createEntityManager();
	}
	
	@AfterEach
	public void afterEach() {
		em.close();
	}

	@Test
	@Order(1)
	public void test_persist() {
		
		em.getTransaction().begin();
		
		var entity = new Student();
		entity.setName("Aung Aung");
		entity.setLastEducation(Education.COLLEGE);
		entity.setDob(LocalDate.of(2000, 1, 1));
		
		em.persist(entity);
		
		em.getTransaction().commit();
	}
	
	@Test
	@Order(1)
	public void test_find() {
		
		em.getTransaction().begin();
		
		var entity = em.find(Student.class, 1);
		
		// Manage State
		assertTrue(em.contains(entity));
		
		em.detach(entity);
		
		// Detached
		assertFalse(em.contains(entity));
		
		entity.setLastEducation(Education.MASTER);
		
		var entity2 = em.merge(entity);
		
		assertFalse(em.contains(entity));
		assertTrue(em.contains(entity2));
		
		entity2.setName("Nilar");
		entity.setName("Thidar");
		
		em.getTransaction().commit();
	}
	
	@Test
	@Order(3)
	public void test_remove() {
		em.getTransaction().begin();
		
		Auditable entity = em.find(Student.class, 1);
		em.remove(entity);
		
		em.getTransaction().commit();
	}
}
