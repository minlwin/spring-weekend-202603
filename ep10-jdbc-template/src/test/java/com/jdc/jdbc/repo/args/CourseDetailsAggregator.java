package com.jdc.jdbc.repo.args;

import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

import com.jdc.jdbc.repo.output.CourseDetails;

public class CourseDetailsAggregator implements ArgumentsAggregator {

	@Override
	public @Nullable Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context)
			throws ArgumentsAggregationException {
		return new CourseDetails(
				accessor.getInteger(0), 
				accessor.getString(1), 
				accessor.getInteger(2), 
				accessor.getInteger(3), 
				accessor.getString(4), 
				accessor.getLong(5));
	}

}
