package com.jdc.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.jdc.demo.domain.entity.Course.Level;
import com.jdc.demo.domain.input.CourseForm;
import com.jdc.demo.domain.input.CourseSearch;
import com.jdc.demo.service.CourseService;

@SpringBootTest
@ActiveProfiles("criteria")
@Sql(
	scripts = {
		"/step1_init_db.sql",
		"/step2_prepare_data.sql"
	}, 
	executionPhase = ExecutionPhase.BEFORE_TEST_METHOD
)
class ApplicationTests {
	
	@Autowired
	private CourseService service;

	@ParameterizedTest
	@CsvSource({
		"Java Basic,Basic,90,Foundation Course",
		"Spring MVC,Intermediate,120,",
		"Full Stack Spring,Advance,180,Job Ready Course",
	})
	void test_create(String name, Level level, int hours, String description) {
		var form = new CourseForm(name, level, hours, description);
		var id = service.create(form);
		assertEquals(11, id);
	}
	
	
	@ParameterizedTest
	@CsvSource({
		",,10",
		"Basic,,3",
		",re,2",
		"Basic,re,1",
		"Advance,re,0"
	})
	void test_search(Level level, String keyword, int size) {
		var form = new CourseSearch(level, keyword);
		var result = service.search(form);
		assertEquals(size, result.size());
	}

}
