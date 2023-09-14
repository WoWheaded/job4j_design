create table films (
    id serial primary key,
    name text,
    production_date date,
	author_id int references author(id));
	
create table author(
	id serial primary key,
	name varchar(255)
);


insert into author(name) VALUES 
('Piter Jacson'),
('Jeorge Lucas'),
('Uve Boll');

insert into films(name, production_date, author_id) values
('Lord of the rings', '2000-01-05', 1),
('Star Wars', '1976-02-05', 2),
('Bloodrayne', '2007-06-07', 3);


select f.name, f.production_date, a.name
from films as f join author as a on f.author_id = a.id;

select f.name as Фильм, f.production_date as Дата_выхода, a.name as Режисер
from films as f join author as a on f.author_id = a.id;

select f.name as "Название фильма", f.production_date "Дата выхода", a.name Режисер
from films f join author a on f.author_id = a.id;