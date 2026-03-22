package com.jdc.jdbc.repo;

import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SpringBootTest
@Sql(
	scripts = {
		"classpath:/schema.sql",
		"classpath:/data.sql"
	}, 
	executionPhase = ExecutionPhase.BEFORE_TEST_METHOD
)
public class StudentRepoTest {

	@ParameterizedTest
	void test_insert(String name, String phone, String email) {
		
	}
	
	@ParameterizedTest
	void test_insert_fails(String name, String phone, String email, String message) {
		
	}
	
	@ParameterizedTest
	void test_findById_found() {
		
	}
	
	@ParameterizedTest
	void test_findById_notFound() {
		
	}
	
	@ParameterizedTest
	void test_update_found() {
		
	}
	
	@ParameterizedTest
	void test_update_error() {
		
	}
	
	@ParameterizedTest
	void test_update_notFound() {
		
	}
	
	@ParameterizedTest
	void test_delete() {
		
	}
	
	@ParameterizedTest
	void test_delete_notFound() {
		
	}
}
