drop table fauna;

create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values
    ('Lion', 10300, '1758-03-14'),
    ('Elephant', 21900, '1758-05-05'),
    ('Penguin', 7300, '1758-09-21'),
    ('Tiger', 20300, '1758-11-29'),
    ('Giraffe', 14300, null),
    ('Superfishes', 7300, '1760-04-08');
	
--1) Извлечение данных, у которых имя name содержит подстроку fish	
select * from fauna WHERE name LIKE '%fish%';	

--2) Извлечение данных, у которых сред. продолжительность жизни находится в диапазоне 10 000 и 21 000
select * from fauna WHERE avg_age >= 10000 AND  avg_age <= 21000;	

--3) Извлечение данных, у которых дата открытия не известна (null)
select * from fauna WHERE discovery_date is null;

--4) Извлечение данных видов, у которых дата открытия раньше 1950 года
select * from fauna WHERE discovery_date < '1950-01-01';