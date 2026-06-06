package com.jdc.demo.domain.entity;

import java.time.LocalDate;
import java.util.List;

import com.jdc.demo.domain.AbstractEntity;
import com.jdc.demo.domain.embeddables.Parent;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@Entity
@Table(uniqueConstraints = {
	@UniqueConstraint(columnNames = {
		"name", "phone", "email"
	})
})
@EqualsAndHashCode(callSuper = true)
public class Student extends AbstractEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String phone;
	@Column(nullable = false)
	private String email;
	
	private Gender gender;
	private LocalDate dob;	
	
	@AttributeOverride(name = "name", column = @Column(name = "father_name"))
	@AttributeOverride(name = "phone", column = @Column(name = "father_phone"))
	@AttributeOverride(name = "occupation", column = @Column(name = "father_occupation"))
	private Parent father;
	
	@AttributeOverride(name = "name", column = @Column(name = "mother_name"))
	@AttributeOverride(name = "phone", column = @Column(name = "mother_phone"))
	@AttributeOverride(name = "occupation", column = @Column(name = "mother_occupation"))
	private Parent mother;
	
	@OneToMany(mappedBy = "student")
	private List<Registration> registrations;
	
	public enum Gender {
		Male, Female
	}
}
