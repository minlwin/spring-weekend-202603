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

import com.jdc.jdbc.repo.args.ClassDetailsAggregator;
import com.jdc.jdbc.repo.input.ClassForm;
import com.jdc.jdbc.repo.output.ClassDetails;
import com.jdc.jdbc.utils.AppBusinessException;

@SpringBootTest
@Sql(
	scripts = {
		"classpath:/schema.sql",
		"classpath:/data.sql"
	}, 
	executionPhase = ExecutionPhase.BEFORE_TEST_METHOD
)
public class ClassRepoTest {
	
	@Autowired
	private ClassRepo repo;

	@ParameterizedTest
	@CsvSource({
		"1,1d,1",
		"5,1m,12"
	})
	void test_insert(int courseId, String startDateStr, Integer months) {
		LocalDate startDate = getDate(startDateStr);
		var result = repo.create(new ClassForm(courseId, startDate, months));
		assertEquals(4, result);
	}
	
	@ParameterizedTest
	@CsvSource({
		"6,1d,1,There is no course with id 6.",
		"5,,12,Start date must be future date.",
		"5,-1d,12,Start date must be future date.",
		"5,1m+,12,Start date must be within 1 month.",
		"5,1d,0,Months must be between 1 and 12.",
		"5,1d,13,Months must be between 1 and 12.",
	})
	void test_insert_fails(int courseId, String startDateStr, Integer months, String message) {
		LocalDate startDate = getDate(startDateStr);
		var error = assertThrows(AppBusinessException.class, () -> repo.create(new ClassForm(courseId, startDate, months)));
		assertEquals(message, error.getMessage());
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/classes.csv")
	void test_findById_found(@AggregateWith(ClassDetailsAggregator.class) ClassDetails details) {
		var result = repo.findById(details.id());
		assertTrue(result.isPresent());
		assertEquals(details, result.get());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {0, 4})
	void test_findById_notFound(int id) {
		var result = repo.findById(id);
		assertTrue(result.isEmpty());
	}
	
	@ParameterizedTest
	@CsvSource({
		"3,1,1d,1",
		"3,5,1m,12"
	})
	void test_update_found(int id, int courseId, String startDateStr, Integer months) {
		LocalDate startDate = getDate(startDateStr);
		var result = repo.update(id, new ClassForm(courseId, startDate, courseId));
		assertEquals(1, result);
	}
	
	@ParameterizedTest
	@CsvSource({
		"3,6,1d,1,There is no course with id 6.",
		"3,5,,12,Start date must be future date.",
		"3,5,-1d,12,Start date must be future date.",
		"3,5,1m+,12,Start date must be within 1 month.",
		"3,5,1d,0,Months must be between 1 and 12.",
		"3,5,1d,13,Months must be between 1 and 12.",
	})
	void test_update_error(int id, int courseId, String startDateStr, Integer months, String message) {
		LocalDate startDate = getDate(startDateStr);
		var error = assertThrows(AppBusinessException.class, () -> repo.update(id, new ClassForm(courseId, startDate, months)));
		assertEquals(message, error.getMessage());
	}
	
	@ParameterizedTest
	@CsvSource({
		"4,1,1d,1",
	})
	void test_update_notFound(int id, int courseId, String startDateStr, Integer months) {
		LocalDate startDate = getDate(startDateStr);
		var result = repo.update(id, new ClassForm(courseId, startDate, courseId));
		assertEquals(0, result);
	}
	
	@ParameterizedTest
	@CsvSource({
		"1,1",
		"3,1",
		"4,0"
	})
	void test_delete(int id, int expected) {
		assertEquals(expected, repo.delete(id));
	}
	
	LocalDate getDate(String dateStr) {
		return dateStr != null ? switch(dateStr) {
		case "1d" -> LocalDate.now().plusDays(1);
		case "-1d" -> LocalDate.now().minusDays(1);
		case "1m" -> LocalDate.now().plusMonths(1);
		case "1m+" -> LocalDate.now().plusMonths(1).plusDays(1);
		default -> null;
		} : LocalDate.now();
	}
}
