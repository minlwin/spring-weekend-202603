DROP TABLE IF EXISTS account;

CREATE TABLE account (
	id int not null identity,
	name varchar(40),
	phone varchar(40),
	email varchar(40)
);