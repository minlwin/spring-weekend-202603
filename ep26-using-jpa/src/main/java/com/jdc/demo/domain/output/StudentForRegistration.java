package com.jdc.demo.domain.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentForRegistration {

	private int id;
	private String fatherName;
	private String fatherPhone;
	private String fatherOccupation;
	private String motherName;
	private String motherPhone;
	private String motherOccupation;

}
