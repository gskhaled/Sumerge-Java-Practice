package com.sumerge;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

enum RestrictionType {
	NONE, PORT, IMMIGRATION, HEALTH, CONDUCTOR;
}

@Repeatable(Restrictions.class)
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Restriction {
	RestrictionType permission() default RestrictionType.NONE;
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Restrictions {
	Restriction[] value();
}

class Person {
	private String id;
	private String name;
	private String lastVisitedPlace;
	private boolean hasFever;
	private boolean hasCough;
	private boolean resultPCR;
	private boolean hasDisability;

	private List<String> specialConditions;
	private List<String> allergies;

	public Person(String id, String name, String lastVisitedPlace, boolean hasFever, boolean hasCough,
			boolean resultPCR, boolean hasDisability, List<String> specialConditions, List<String> allergies) {
		super();
		this.id = id;
		this.name = name;
		this.lastVisitedPlace = lastVisitedPlace;
		this.hasFever = hasFever;
		this.hasCough = hasCough;
		this.resultPCR = resultPCR;
		this.hasDisability = hasDisability;
		this.specialConditions = specialConditions;
		this.allergies = allergies;
	}

	public String getId(Object o) {
		return id;
	}

	@Restriction(permission = RestrictionType.PORT)
	@Restriction(permission = RestrictionType.IMMIGRATION)
	@Restriction(permission = RestrictionType.HEALTH)
	@Restriction(permission = RestrictionType.CONDUCTOR)
	public String getName(Object o) {
		return name;
	}

	@Restriction(permission = RestrictionType.IMMIGRATION)
	@Restriction(permission = RestrictionType.HEALTH)
	public String getLastVisitedPlace(Object o) {
		return lastVisitedPlace;
	}

	@Restriction(permission = RestrictionType.HEALTH)
	@Restriction(permission = RestrictionType.CONDUCTOR)
	public boolean isHasFever(Object o) {
		return hasFever;
	}

	@Restriction(permission = RestrictionType.HEALTH)
	@Restriction(permission = RestrictionType.CONDUCTOR)
	public boolean isHasCough(Object o) {
		return hasCough;
	}

	@Restriction(permission = RestrictionType.PORT)
	@Restriction(permission = RestrictionType.IMMIGRATION)
	@Restriction(permission = RestrictionType.HEALTH)
	public boolean isResultPCR(Object o) {
		return resultPCR;
	}

	public boolean isHasDisability(Object o) {
		return hasDisability;
	}

	@Restriction(permission = RestrictionType.PORT)
	@Restriction(permission = RestrictionType.IMMIGRATION)
	@Restriction(permission = RestrictionType.HEALTH)
	public List<String> getSpecialConditions(Object o) {
		return specialConditions;
	}

	@Restriction(permission = RestrictionType.HEALTH)
	public List<String> getAllergies(Object o) {
		return allergies;
	}
}

public class Airport {
	public static void main(String[] args) {
		ArrayList<Person> people = new ArrayList<Person>();
		people.add(new Person("111", "Gasser", "Egypt", false, false, false, false, new ArrayList<String>(),
				new ArrayList<String>()));
		people.add(new Person("222", "Ahmed", "USA", true, true, false, false, new ArrayList<String>(),
				new ArrayList<String>()));
		people.add(new Person("333", "Mohammed", "Canada", false, false, false, false, new ArrayList<String>(),
				new ArrayList<String>()));
		people.add(new Person("444", "Ali", "Egypt", false, false, false, false, new ArrayList<String>(),
				new ArrayList<String>()));

		Class<Person> annotatedClass = Person.class;
		Method[] methods = annotatedClass.getMethods();
		String[] officers = { "Port", "Immigration", "Health", "PCR test" };

	}
}