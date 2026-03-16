package com.jdc.jdbc.repo.input;

public record CourseSearch(
		String keyword,
		int feesFrom,
		int feesTo) {

}
