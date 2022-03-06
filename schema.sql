CREATE DATABASE test;

CREATE TABLE IF NOT EXISTS manager (
    manager_id integer not null,
    last_name varchar(50) not null,
	first_name varchar(50) not null,
	middle_name varchar(50),
	telephone integer not null,
	alternate_id integer references manager(manager_id),
	primary key (manager_id)
 );

 CREATE TABLE IF NOT EXISTS client (
    client_id integer primary key,
    name text not null,
	address text not null,
	manager_id integer not null references manager(manager_id),
	is_deleted boolean
 );