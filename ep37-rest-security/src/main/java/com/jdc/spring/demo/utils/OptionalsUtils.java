package com.jdc.spring.demo.utils;

import java.util.Optional;
import java.util.function.Function;

import com.jdc.spring.demo.utils.exceptions.BusinessRuleViolationException;

public class OptionalsUtils {

	public static<T, V> Function<String, Function<String, Function<V, T>>> safeCall(Optional<T> optional) {
		return resource -> type -> value -> optional.orElseThrow(() -> new BusinessRuleViolationException("There is no %s with %s %s.".formatted(resource, type, value)));
	}
}
