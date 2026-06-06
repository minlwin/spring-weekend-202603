package com.jdc.demo.service.base;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.demo.domain.entity.Classes;
import com.jdc.demo.domain.entity.Registration;
import com.jdc.demo.domain.entity.Student;
import com.jdc.demo.domain.input.RegistrationForm;
import com.jdc.demo.domain.output.RegistrationDetails;
import com.jdc.demo.service.RegistrationService;
import com.jdc.demo.service.StudentService;

import jakarta.persistence.EntityManager;

public abstract class AbstractRegistrationService implements RegistrationService {
	
	@Autowired
	protected EntityManager entityManager;
	
	@Autowired
	private StudentService studentService;

	@Override
	@Transactional(readOnly = true)
	public RegistrationDetails findById(UUID id) {
		var entity = entityManager.find(Registration.class, id);
		return RegistrationDetails.from(entity);
	}
	
	@Override
	@Transactional
	public UUID create(int classId, RegistrationForm form) {
		
		var intake = entityManager.getReference(Classes.class, classId);
		
		var studentId = studentService.findId(form.getStudentName(), form.getStudentPhone(), form.getStudentEmail());
		
		var student = Optional.ofNullable(studentId)
				.map(sid -> entityManager.getReference(Student.class, sid))
				.orElseGet(() -> {
					var entity = new Student();
					entity.setName(form.getStudentName());
					entity.setPhone(form.getStudentPhone());
					entity.setEmail(form.getStudentEmail());
					entityManager.persist(entity);
					return entity;
				});
		
		student.setFather(form.getFather());
		student.setMother(form.getMother());
		
		var registration = new Registration();
		registration.setStudent(student);
		registration.setIntake(intake);
		registration.setRegistDate(LocalDateTime.now());
		registration.setPaid(intake.getFees());
		
		entityManager.persist(registration);
		
		return registration.getId();
	}
}
