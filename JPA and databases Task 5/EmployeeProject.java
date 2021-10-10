package com.example;

import javax.persistence.*;

@Table(name = "employeeprojects", indexes = {
        @Index(name = "project", columnList = "project"),
        @Index(name = "employee", columnList = "employee")
})
@Entity
public class EmployeeProject {
    @ManyToOne
    @JoinColumn(name = "employee")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "project")
    private Project project;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}