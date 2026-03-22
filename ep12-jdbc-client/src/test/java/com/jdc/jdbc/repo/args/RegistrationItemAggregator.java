package com.jdc.jdbc.repo.args;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

import com.jdc.jdbc.repo.output.RegistrationItem;

public class RegistrationItemAggregator implements ArgumentsAggregator {
	
	private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Override
	public @Nullable Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context)
			throws ArgumentsAggregationException {
		return new RegistrationItem(
				accessor.getInteger(0), 
				accessor.getInteger(1), 
				accessor.getString(2), 
				accessor.getInteger(3), 
				LocalDate.parse(accessor.getString(4), DF), 
				accessor.getInteger(5), 
				accessor.getString(6), 
				accessor.getString(7), 
				accessor.getString(8));
	}

}
