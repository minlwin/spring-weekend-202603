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

import com.jdc.jdbc.repo.args.CourseDetailsAggregator;
import com.jdc.jdbc.repo.input.CourseForm;
import com.jdc.jdbc.repo.input.CourseSearch;
import com.jdc.jdbc.repo.output.CourseDetails;
import com.jdc.jdbc.utils.AppBusinessException;

@SpringBootTest
@Sql(
	scripts = {
		"classpath:/schema.sql",
		"classpath:/data.sql"
	}, 
	executionPhase = ExecutionPhase.BEFORE_TEST_METHOD
)
public class CourseRepoTest {

	@Autowired
	private CourseRepo repo;
	
	@ParameterizedTest
	@CsvSource({
		"Vibe Coding Master,6,600000,,6",
		"Vibe Coding Foundation,6,300000,Test,6",
		"Vibe Coding Foundation,6,0,Test,6",
	})
	void test_insert(String name, int hours, int fees, String description, int expected) {
		var form = new CourseForm(name, hours, fees, description);
		var result = repo.create(form);
		assertEquals(expected, result);
	}
	
	@ParameterizedTest
	@CsvSource({
		"Java Fullstack,6,600000,,Java Fullstack course is already created.",
		"Vibe Coding Foundation,0,300000,Test,Hours must be greater than Zero.",
		"Vibe Coding Foundation,-1,300000,Test,Hours must be greater than Zero.",
		"Vibe Coding Foundation,1,-1,Test,Fees must not be negative value.",
	})
	void test_insert_fails(String name, int hours, int fees, String description, String expected) {
		var form = new CourseForm(name, hours, fees, description);
		var error = assertThrows(AppBusinessException.class, () -> repo.create(form));
		assertEquals(expected, error.getMessage());
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/courses.csv")
	void test_findById_found(@AggregateWith(CourseDetailsAggregator.class) CourseDetails details) {
		var result = repo.findById(details.id());
		assertTrue(result.isPresent());
		assertEquals(details, result.get());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {
		0, 6
	})
	void test_findById_notFound(int id) {
		var result = repo.findById(id);
		assertTrue(result.isEmpty());
	}
	
	@ParameterizedTest
	@CsvSource({
		"1,New Course,,,",
		"1,,20,,",
		"1,,,100,",
		"1,,,0,",
		"1,,,,Test Description",
	})
	void test_update_found(int id, String name, Integer hours, Integer fees, String description) {
		var result = repo.update(id, new CourseForm(name, hours, fees, description));
		assertEquals(1, result);
	}
	
	@ParameterizedTest
	@CsvSource({
		"1,,,,There is no fields for update course.",
		"1,Java Fullstack,,,,Java Fullstack is already created.",
		"1,,0,,Hours must be greater than Zero.",
		"1,,-1,,Hours must be greater than Zero.",
		"1,,,-1,Fees must not be negative value.",
	})
	void test_update_error(int id, String name, Integer hours, Integer fees, String description, String message) {
		var error = assertThrows(AppBusinessException.class, () -> repo.update(id, new CourseForm(name, hours, fees, description)));
		assertEquals(message, error.getMessage());
	}
	
	@ParameterizedTest
	@CsvSource({
		"6,New Course,,,",
	})
	void test_update_notFound(int id, String name, Integer hours, Integer fees, String description) {
		var result = repo.update(id, new CourseForm(name, hours, fees, description));
		assertEquals(0, result);
	}
	
	@ParameterizedTest
	@ValueSource(ints = {1, 5})
	void test_delete(int id) {
		assertEquals(1, repo.delete(id));
	}
	
	@ParameterizedTest
	@ValueSource(ints = {0, 6})
	void test_delete_notFound(int id) {
		assertEquals(0, repo.delete(id));
	}
	
	@ParameterizedTest
	@CsvSource({
		",,,5", // If there is no search conditions, should fetch all rows
		"Java,,,1", // If keyword is Java should fetch 1 row,
		"Javas,,,0", // If keyword is Javas should fetch 0 row,
		",450,,3", // If Fees From is 450, should fetch 3 rows,
		",,400,2", // If Fees To is 400, should fetch 2 rows,
	})
	void test_search(String keyword, Integer feesFrom, Integer feesTo, int expected) {
		var result = repo.search(new CourseSearch(keyword, feesFrom, feesTo));
		assertEquals(expected, result.size());
	}

}
