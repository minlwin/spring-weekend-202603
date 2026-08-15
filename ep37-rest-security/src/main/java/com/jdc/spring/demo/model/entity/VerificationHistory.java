package com.jdc.spring.demo.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class VerificationHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(nullable = false)
	private String code;
	
	@Column(nullable = false)
	private Action action;
	
	@ManyToOne
	private Account account;
	
	@Column(nullable = false)
	private LocalDateTime sendAt;
	
	public enum Action {
		ForgotPassword, CreateEmployee, CustomerSignUp
	}
}
