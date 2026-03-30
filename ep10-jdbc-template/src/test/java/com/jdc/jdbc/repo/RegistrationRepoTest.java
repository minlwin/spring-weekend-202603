package com.jdc.jdbc.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.jdc.jdbc.repo.args.RegistrationItemAggregator;
import com.jdc.jdbc.repo.input.RegistrationSearch;
import com.jdc.jdbc.repo.output.RegistrationItem;
import com.jdc.jdbc.utils.AppBusinessException;

@SpringBootTest
@Sql(
	scripts = {
		"classpath:/schema.sql",
		"classpath:/data.sql"
	},
	executionPhase = ExecutionPhase.BEFORE_TEST_METHOD
)
public class RegistrationRepoTest {

	@Autowired
	private RegistrationRepo repo;

	@ParameterizedTest
	@CsvSource({
		"1,10,001000010",
		"2,8,002000008",
		"3,15,003000015",
	})
	void test_create(int classId, int studentId, String expected) {
		var registrationCode = repo.create(classId, studentId);
		assertEquals(expected, registrationCode);
	}
	
	@ParameterizedTest
	@CsvSource({
		"6,1,There is no class with id 6.",
		"1,17,There is no student with id 17.",
		"6,17,There is no class with id 6.",
		"1,1,Registration with id 001000001 is already created."
	})
	void test_create_error(int classId, int studentId, String expected) {
		var exception = assertThrows(AppBusinessException.class, 
				() -> repo.create(classId, studentId));
		assertEquals(expected, exception.getMessage());
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/registration.csv")
	void test_findById_found(@AggregateWith(RegistrationItemAggregator.class) RegistrationItem item) {
		var result = repo.findById(item.getCode());
		assertTrue(result.isPresent());
		assertEquals(item, result.get());
	}
	
	@ParameterizedTest
	@ValueSource(strings = {
		"001010000",
		"010000001"
	})
	void test_findById_notfound(String code) {
		var result = repo.findById(code);
		assertTrue(result.isEmpty());
	}
	
	@ParameterizedTest
	@CsvSource({
		",,,20",
		"Java,,,8",
		"Alice,,,2",
		"Java,0,,8",
		"Java,-1,,8",
		"Java,1,,0",
		"Java,,0,8",
		"Java,,-1,0",
		"Java,,1,8",
	})
	void test_search(String key, String fromStr, String toStr, int expected) {
		LocalDate from = getDate(fromStr);
		LocalDate to = getDate(toStr);
		
		var result = repo.search(new RegistrationSearch(key, from, to));
		assertEquals(expected, result.size());
	}
	
	@ParameterizedTest
	@CsvSource({
		"001000009,0",
		"001000008,1",
	})
	void test_delete(String code, int expected) {
		var result = repo.delete(code);
		assertEquals(expected, result);
	}
	
	LocalDate getDate(String dateStr) {
		return null != dateStr ? switch(dateStr) {
		case "0" -> LocalDate.now();
		case "1" -> LocalDate.now().plusDays(1);
		case "-1" -> LocalDate.now().minusDays(1);
		default -> null;
		} : null;
	}
}
