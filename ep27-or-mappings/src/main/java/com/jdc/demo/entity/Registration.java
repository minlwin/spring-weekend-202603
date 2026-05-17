package com.jdc.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;

@Data
@Entity
@IdClass(value = RegistrationId.class)
public class Registration {

	@Id
	private LocalDate registerAt;
	@Id
	private int seqNumber;
	
	private String remark;
}
