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

	public String getId() {
		return id;
	}

	@Restriction(permission = RestrictionType.PORT)
	@Restriction(permission = RestrictionType.IMMIGRATION)
	@Restriction(permission = RestrictionType.HEALTH)
	@Restriction(permission = RestrictionType.CONDUCTOR)
	public String getName() {
		return name;
	}

	@Restriction(permission = RestrictionType.IMMIGRATION)
	@Restriction(permission = RestrictionType.HEALTH)
	public String getLastVisitedPlace() {
		return lastVisitedPlace;
	}

	@Restriction(permission = RestrictionType.HEALTH)
	@Restriction(permission = RestrictionType.CONDUCTOR)
	public boolean isHasFever() {
		return hasFever;
	}

	@Restriction(permission = RestrictionType.HEALTH)
	@Restriction(permission = RestrictionType.CONDUCTOR)
	public boolean isHasCough() {
		return hasCough;
	}

	@Restriction(permission = RestrictionType.PORT)
	@Restriction(permission = RestrictionType.IMMIGRATION)
	@Restriction(permission = RestrictionType.HEALTH)
	public boolean isResultPCR() {
		return resultPCR;
	}

	public void setResultPCR(boolean res) {
		this.resultPCR = res;
	}

	public boolean isHasDisability() {
		return hasDisability;
	}

	@Restriction(permission = RestrictionType.PORT)
	@Restriction(permission = RestrictionType.IMMIGRATION)
	@Restriction(permission = RestrictionType.HEALTH)
	public List<String> getSpecialConditions() {
		return specialConditions;
	}

	@Restriction(permission = RestrictionType.HEALTH)
	public List<String> getAllergies() {
		return allergies;
	}
}

public class Airport {
	public static ArrayList<String> getDataAccessible(Method[] methods, String officerType) {
		ArrayList<String> res = new ArrayList<String>();
		for (Method method : methods) {
			if (method.isAnnotationPresent(Restrictions.class)) {
				Restriction[] r = method.getAnnotation(Restrictions.class).value();
				for (int i = 0; i < r.length; i++) {
					String allowedOfficer = r[i].permission().name();
					if (allowedOfficer.compareToIgnoreCase(officerType) == 0)
						res.add(method.getName());
				}
			}
			if (method.isAnnotationPresent(Restriction.class)) {
				RestrictionType r = method.getAnnotation(Restriction.class).permission();
				if (r.name().compareToIgnoreCase(officerType) == 0)
					res.add(method.getName());
			}
		}
		return res;
	}

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

		Method[] methods = Person.class.getMethods();
		String[] officers = { "Port", "Immigration", "Health" };
		ArrayList<Person> sentToConductor = new ArrayList<Person>();
		for (String officer : officers) {
			System.out.println("Arrived at the " + officer + " officer");
			System.out.println("------------------------------------------");
			for (Person person : people) {
				System.out.println("Person " + person.getName() + " arrived");
				System.out.println("Data seen:");
				System.out.println(getDataAccessible(methods, officer));
				if (officer.equals("Health")) {
					if (person.isHasCough() && person.isHasFever()) {
						sentToConductor.add(person);
						System.out.println("~~~~NEEDS TO BE SENT TO CONDUCTOR!");
					}
				}
			}
		}
		System.out.println("Arrived at the Conductor officer");
		System.out.println("------------------------------------------");
		for (Person person : sentToConductor) {
			System.out.println("Person " + person.getName() + " arrived");
			System.out.println("Data seen:");
			System.out.println(getDataAccessible(methods, "conductor"));
			person.setResultPCR(false);
			System.out.println("PCR result negative.");
		}
	}
}