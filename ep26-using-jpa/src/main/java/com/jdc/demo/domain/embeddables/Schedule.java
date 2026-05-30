package com.jdc.demo.domain.embeddables;

import java.time.DayOfWeek;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Schedule {

	private DayOfWeek days;
	private String startTime;
	private String endTimes;
}
