package com.jdc.demo.domain.output;

import java.time.LocalDate;

import com.jdc.demo.domain.entity.Course.Level;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassesListItem {

	private int id;
	private int courseId;
	private String courseName;
	private Level courseLevel;
	private LocalDate startDate;
	private int fees;
	private int availableSeats;
	private long registrations;
}
