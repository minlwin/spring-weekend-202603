package com.jdc.jdbc.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

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
		"4,1,There is no class with id 4.",
		"1,16,There is no student with id 16.",
		"4,16,There is no class with id 4.",
		"1,1,Registration with id 001000001 is already created."
	})
	void test_create_error(int classId, int studentId, String expected) {
		var exception = assertThrows(AppBusinessException.class, 
				() -> repo.create(classId, studentId));
		assertEquals(expected, exception.getMessage());
	}
}
