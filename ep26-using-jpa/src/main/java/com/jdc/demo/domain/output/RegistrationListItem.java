package com.jdc.demo.domain.output;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationListItem {
	private UUID id;
	private int courseId;
	private String curseName;
	private LocalDate intake;
	private int studentId;
	private String studentName;
	private String studentPhone;
	private String studentEmail;
	private LocalDateTime registerAt;
}
