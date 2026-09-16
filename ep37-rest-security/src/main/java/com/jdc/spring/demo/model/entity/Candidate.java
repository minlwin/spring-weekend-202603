package com.jdc.spring.demo.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jdc.spring.demo.model.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Candidate extends AbstractEntity{

	@Id
	private int id;
	
	@MapsId
	@OneToOne(optional = false)
	private Account account;
	
	@Column(nullable = false)
	private LocalDateTime registerdAt;
	
	private LocalDateTime verifiedAt;
	
	private String phone;
	private Gender gender;
	private LocalDate dob;
	
	private String selfie;
	private String jobTitle;
	private String biography;
	
	private int expectedSalaryFrom;
	private int expectedSalaryTo;
	
	private Status status;
	
	public enum Gender {
		Male, Female
	}
	
	public enum Status {
		ActivelyLooking("Actively Looking"), 
		OpenOffer("Open Offer"), 
		NotLooking("Not Looking");
		
		private String value;
		
		private Status(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
	}

}
