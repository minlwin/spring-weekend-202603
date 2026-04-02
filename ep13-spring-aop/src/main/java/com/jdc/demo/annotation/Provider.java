package com.jdc.demo.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

@Component
@Retention(CLASS)
@Target(TYPE)
public @interface Provider {

}
