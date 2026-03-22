package com.jdc.jdbc.repo.input;

public record CourseSearch(
		String keyword,
		Integer feesFrom,
		Integer feesTo) {

}
