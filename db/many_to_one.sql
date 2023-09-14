create table role(
	id serial primary key,
	name varchar(50)
);

create table users(
	id serial primary key,
    name varchar(255),
    role_id int references role(id)
);

insert into role(name) values ('admin');
insert into users(name, role_id) VALUES ('Dmitry', 1);

select * from users;

select * from role where id in (select role_id from users);