package com.jdc.jdbc.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.jdc.jdbc.repo.input.CourseForm;
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
	
}
