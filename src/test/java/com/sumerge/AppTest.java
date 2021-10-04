package com.sumerge;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;

public class AppTest {

	@Test
	public void testTrue() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		DoctorsAppointment appointment = new DoctorsAppointment("Gasser Khaled Salah", 23, "10 Maadi street",
				"gskhaled@ymail.com", "Dr. Ahmed Ahmed", new Date(2021, 10, 30));

		Set<ConstraintViolation<DoctorsAppointment>> violations = validator.validate(appointment);
		assertTrue(violations.isEmpty());
	}

	@Test
	public void testFalse() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		DoctorsAppointment appointment = new DoctorsAppointment("Gasser Khaled Salah", 23, "10 Maadi street",
				"gskhaled@ymail.com", "Dr", new Date(2021, 10, 30));

		Set<ConstraintViolation<DoctorsAppointment>> violations = validator.validate(appointment);
		assertTrue(!violations.isEmpty());
	}

}
