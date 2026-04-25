package com.jdc.demo.annotation;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

@Component
@Target(TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Provider {

}
