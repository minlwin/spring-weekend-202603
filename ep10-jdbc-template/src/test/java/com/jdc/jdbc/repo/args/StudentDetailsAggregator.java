package com.jdc.jdbc.repo.args;

import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

import com.jdc.jdbc.repo.output.StudentDetails;

public class StudentDetailsAggregator implements ArgumentsAggregator {

	@Override
	public @Nullable Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context)
			throws ArgumentsAggregationException {
		return new StudentDetails(
				accessor.getInteger(0), 
				accessor.getString(1), 
				accessor.getString(2), 
				accessor.getString(3), 
				accessor.getLong(4));
	}

}
