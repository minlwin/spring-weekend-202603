package com.jdc.demo.domain.embeddables;

import java.time.DayOfWeek;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Embeddable
public class Schedule {

	@NotNull(message = "Dasy is required.")
	private DayOfWeek days;
	@NotBlank(message = "Start Time is required.")
	private String startTime;
	@NotBlank(message = "End Time is required.")
	private String endTime;
}
