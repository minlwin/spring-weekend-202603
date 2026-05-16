create table COURSE (
	id int primary key auto_increment,
    name varchar(255) not null,
    level varchar(255) not null,
    hours int not null,
    description text
);