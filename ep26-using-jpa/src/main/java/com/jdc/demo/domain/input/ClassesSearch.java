package com.jdc.demo.domain.input;

import java.time.LocalDate;

import com.jdc.demo.domain.entity.Classes.Status;

import lombok.Data;

@Data
public class ClassesSearch {

	private Integer courseId;
	private Status status;
	private LocalDate startFrom;
}
