package com.jdc.demo.domain.entity;

import java.time.LocalDate;
import java.util.List;

import com.jdc.demo.domain.AbstractEntity;
import com.jdc.demo.domain.embeddables.Schedule;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Classes extends AbstractEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(optional = false)
	private Course course;
	
	@Column(nullable = false)
	private LocalDate startDate;

	@Column(nullable = false)
	private int months;
	
	@ElementCollection
	private List<Schedule> schedules;
	
	@Column(nullable = false)
	private int fees;

	@Column(nullable = false)
	private int availableSeats;
	
	@Column(nullable = false)
	private Status status;
	
	@OneToMany(mappedBy = "intake")
	private List<Registration> registrations;
	
	public enum Status {
		Available, Started, Finished
	}
}
