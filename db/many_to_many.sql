create table computers(
	id serial primary key,
	name varchar(50)
);

create table school_users(
	id serial primary key,
    name varchar(50)
);

create table users_computers(
     id serial primary key,
     computers_id int references computers(id),
     school_users_id int references school_users(id)
 );

insert into computers(name) values ('Lenovo');
insert into computers(name) values ('HP');
insert into computers(name) values ('Asus');

insert into school_users(name) values ('Dmitry');
insert into school_users(name) values ('Dasha');
insert into school_users(name) values ('Anton');

insert into users_computers(computers_id, school_users_id) values (1, 1);
insert into users_computers(computers_id, school_users_id) values (1, 2);
insert into users_computers(computers_id, school_users_id) values (1, 3);
insert into users_computers(computers_id, school_users_id) values (2, 1);
insert into users_computers(computers_id, school_users_id) values (2, 2);
insert into users_computers(computers_id, school_users_id) values (3, 3);