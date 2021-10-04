package com.sumerge;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class DoctorsAppointment {
	@NotNull(message = "Patient name cannot be null")
	@Size(min = 10, max = 100, message = "Patient name must be between 10 and 100 characters")
	private String patientName;

	@NotNull(message = "Age cannot be null")
	@Min(value = 0, message = "Age should be a positive number")
	@Max(value = 150, message = "Age should not be greater than 150")
	private int age;

	@NotNull(message = "Address cannot be null")
	// to contain at least one number: using neg lookahead (?!^[0-9]*$)
	// to contain at least one letter: using neg lookahead(?!^[a-zA-Z]*$)
	// to have numbers, letters and spaces: ([a-zA-Z0-9 ]+)
	@Pattern(regexp = "(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9 ]+)", message = "Address must contain street name and building number")
	private String patientAddress;

	@NotNull(message = "Email cannot be null")
	@Email(message = "Email should be valid")
	private String email;

	@NotNull(message = "Doctor name cannot be null")
	@Size(min = 10, max = 100, message = "Doctor name must be between 10 and 100 characters")
	private String doctortName;

	@NotNull(message = "Appointment date cannot be null")
	@FutureOrPresent(message = "Appointment date cannot be in the past")
	private Date appointmentDate;

	@Override
	public String toString() {
		return "Appointment information: \n Patient name: " + patientName + "\n Patient age: " + age
				+ "\n Patient address: " + patientAddress + "\n Patient email: " + email + "\n Doctor name: "
				+ doctortName + "\n Appointment date: " + appointmentDate;
	}

	public DoctorsAppointment() {
	}

	public DoctorsAppointment(String patientName, int age, String patientAddress, String email, String doctortName,
			Date appointmentDate) {
		this.patientName = patientName;
		this.age = age;
		this.patientAddress = patientAddress;
		this.email = email;
		this.doctortName = doctortName;
		this.appointmentDate = appointmentDate;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDoctortName(String doctortName) {
		this.doctortName = doctortName;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public static void main(String[] args) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		DoctorsAppointment appointment = new DoctorsAppointment();
		appointment.setPatientName("Gasser Khaled Salah");
		appointment.setAge(23);
		appointment.setPatientAddress("10 Maadi street");
		appointment.setEmail("gskhaled@ymail.com");
		appointment.setDoctortName("Dr. Ahmed Ahmed");
		appointment.setAppointmentDate(new Date(2021, 10, 30));

		Set<ConstraintViolation<DoctorsAppointment>> violations = validator.validate(appointment);
		for (ConstraintViolation<DoctorsAppointment> violation : violations) {
			System.out.println(violation.getMessage());
		}

		System.out.println(appointment);
	}
}
