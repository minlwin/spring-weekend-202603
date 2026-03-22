package com.jdc.jdbc.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.jdc.jdbc.repo.args.StudentDetailsAggregator;
import com.jdc.jdbc.repo.input.StudentForm;
import com.jdc.jdbc.repo.input.StudentSearch;
import com.jdc.jdbc.repo.output.StudentDetails;
import com.jdc.jdbc.utils.AppBusinessException;

@SpringBootTest
@Sql(
	scripts = {
		"classpath:/schema.sql",
		"classpath:/data.sql"
	}, 
	executionPhase = ExecutionPhase.BEFORE_TEST_METHOD
)
public class StudentRepoTest {
	
	@Autowired
	private StudentRepo repo;

	@ParameterizedTest
	@CsvSource({
		"Mikel Owen,+959-1111-2222,mike@gmail.com",
		"Mikel Owen,+959 1111 2222,mike@gmail.com",
		"Mikel Owen,09 1111 2222,mike@gmail.com",
		"Mikel Owen,091112222,mike@gmail.com",
	})
	void test_insert(String name, String phone, String email) {
		var result = repo.create(new StudentForm(name, phone, email));
		assertEquals(16, result);
	}
	
	@ParameterizedTest
	@CsvSource({
		"Mikel Owen,+959-1111-2222a,mike@gmail.com,Invalid phone number.",
		"Mikel Owen,+959 1111 2222a,mike@gmail.com,Invalid phone number.",
		"Mikel Owen,09 1111 2222a,mike@gmail.com,Invalid phone number.",
		"Mikel Owen,a091112222,mike@gmail.com,Invalid phone number.",
		"Mikel Owen,091112222,mikegmail.com,Invalid email address.",
		"Mikel Owen,091112222,mike@gmailcom,Invalid email address.",
		"Mikel Owen,091112222,alice@example.com,Email is already used.",
	})
	void test_insert_fails(String name, String phone, String email, String message) {
		var error = assertThrows(AppBusinessException.class, () -> repo.create(new StudentForm(name, phone, email)));
		assertEquals(message, error.getMessage());
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/students.csv")
	void test_findById_found(@AggregateWith(StudentDetailsAggregator.class) StudentDetails details) {
		var result = repo.findById(details.id());
		assertTrue(result.isPresent());
		assertEquals(details, result);
	}
	
	@ParameterizedTest
	@ValueSource(ints = {0, 16})
	void test_findById_notFound(int id) {
		var result = repo.findById(id);
		assertTrue(result.isEmpty());
	}
	
	@ParameterizedTest
	@CsvSource({
		"15,Mikel Owen,+959-1111-2222,mike@gmail.com",
		"15,Mikel Owen,+959 1111 2222,mike@gmail.com",
		"15,Mikel Owen,09 1111 2222,mike@gmail.com",
		"15,Mikel Owen,091112222,mike@gmail.com",
	})
	void test_update_found(int id, String name, String phone, String email) {
		assertEquals(1, repo.update(id, new StudentForm(name, phone, email)));
	}
	
	@ParameterizedTest
	@CsvSource({
		"15,,,,There is no fields for update student.",
		"15,Mikel Owen,+959-1111-2222a,mike@gmail.com,Invalid phone number.",
		"15,Mikel Owen,+959 1111 2222a,mike@gmail.com,Invalid phone number.",
		"15,Mikel Owen,09 1111 2222a,mike@gmail.com,Invalid phone number.",
		"15,Mikel Owen,a091112222,mike@gmail.com,Invalid phone number.",
		"15,Mikel Owen,091112222,mikegmail.com,Invalid email address.",
		"15,Mikel Owen,091112222,mike@gmailcom,Invalid email address.",
		"16,Mikel Owen,091112222,alice@example.com,Email is already used.",
	})
	void test_update_error(int id, String name, String phone, String email, String message) {
		var error = assertThrows(AppBusinessException.class, () -> repo.update(id, new StudentForm(name, phone, email)));
		assertEquals(message, error.getMessage());
	}
	
	@ParameterizedTest
	void test_update_notFound(int id, String name, String phone, String email) {
		assertEquals(0, repo.update(id, new StudentForm(name, phone, email)));
	}
	
	@ParameterizedTest
	@CsvSource({
		"1,1",
		"15,1",
		"16,0"
	})
	void test_delete(int id, int expected) {
		assertEquals(expected, repo.delete(id));
	}
	
	@ParameterizedTest
	@CsvSource({
		",15",
		"Alice,1",
		"alice,1",
		"aLice,1",
		"aLice1,0",
	})
	void test_search(String keyword, int expected) {
		var result = repo.search(new StudentSearch(keyword));
		assertEquals(expected, result.size());
	}
	
}
