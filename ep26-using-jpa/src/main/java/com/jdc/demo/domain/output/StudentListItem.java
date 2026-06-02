package com.jdc.demo.domain.output;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentListItem {
	private int id;
	private String name;
	private String phone;
	private String email;
	private LocalDateTime registerAt;
	private long registrations;
	private String parentName;
	private String parentPhone;
}
