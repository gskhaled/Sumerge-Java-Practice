CREATE TABLE EmployeeProjects (
	employee smallint,
    project smallint,
    foreign key (employee) references Employees(id),
    foreign key (project) references Projects(id)
);