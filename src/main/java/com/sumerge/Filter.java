package com.sumerge;

import java.util.ArrayList;
import java.util.List;

public class Filter {

	public static class Employee {
		String title;
		String name;
		String mobile;

		public Employee(String title, String name, String mobile) {
			this.title = title;
			this.name = name;
			this.mobile = mobile;
		}

	}

	public static void filterList(List<Employee> employeesList) {
		ArrayList<String> titles = new ArrayList<String>();
		for (Employee emp : employeesList) {
			if (!titles.contains(emp.title))
				titles.add(emp.title);
		}
		for (String title : titles) {
			int count = 0;
			ArrayList<String> employeesWithTitle = new ArrayList<String>();
			for (Employee emp : employeesList) {
				if (emp.title.equals(title)) {
					employeesWithTitle.add("Name: " + emp.name + " Mobile: " + emp.mobile);
					count++;
				}
			}
			if (count < 2)
				title = "Special Title";
			System.out.println("Title: " + title + ", count: " + count);
			System.out.println(employeesWithTitle);
		}
	}

	public static void main(String[] args) {
		List<Employee> employeesList = new ArrayList<Employee>();
		employeesList.add(new Employee("ASE", "Gasser", "0100"));
		employeesList.add(new Employee("ASE", "Tarek", "0100"));
		employeesList.add(new Employee("SE", "Kadry", "0100"));
		employeesList.add(new Employee("SE", "Hussain", "0100"));
		employeesList.add(new Employee("SE", "M. Adel", "0100"));
		employeesList.add(new Employee("TM", "Mohd. Ghanem", "0100"));
		employeesList.add(new Employee("CEO", "Shady", "0100"));
		filterList(employeesList);
	}

}
