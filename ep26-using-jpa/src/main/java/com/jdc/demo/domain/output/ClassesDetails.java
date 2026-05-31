package com.jdc.demo.domain.output;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import com.jdc.demo.domain.embeddables.Schedule;
import com.jdc.demo.domain.entity.Classes.Status;
import com.jdc.demo.domain.entity.Course.Level;

import lombok.Data;

@Data
public class ClassesDetails {

	private int id;
	private int courseId;
	private String courseName;
	private Level courseLevel;
	private String courseDescription;
	
	private LocalDate startDate;
	private BigInteger fees;
	private int availableSeats;
	private int registrations;
	private Status status;
	
	private List<Schedule> schedules;
}
