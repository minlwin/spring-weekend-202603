package com.jdc.jdbc.repo.args;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

import com.jdc.jdbc.repo.output.ClassDetails;

public class ClassDetailsAggregator implements ArgumentsAggregator {
	
	private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Override
	public @Nullable Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context)
			throws ArgumentsAggregationException {
		return new ClassDetails(
				accessor.getInteger(0), 
				accessor.getInteger(1), 
				accessor.getString(2), 
				LocalDate.parse(accessor.getString(3), DF), 
				accessor.getInteger(4), 
				accessor.getInteger(5), 
				accessor.getLong(6));
	}

}
