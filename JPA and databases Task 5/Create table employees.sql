CREATE TABLE Employees (
	id smallint not null auto_increment,
	name varchar(50) not null,
    email varchar(50) not null unique,
    phone_number varchar(13) not null unique,
    age tinyint not null,
    nationalID varchar(20) not null unique,
    role varchar(20),
    primary key (id),
    foreign key (role) references roles(name)
);