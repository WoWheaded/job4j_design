create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

INSERT INTO devices (name, price) VALUES
    ('Laptop', 999.99),
    ('Smartphone', 499.99),
    ('Tablet', 299.99),
    ('Desktop PC', 799.99);

INSERT INTO people (name) VALUES
    ('John Smith'),
    ('Alice Johnson'),
    ('Bob Brown'),
    ('Eva Davis');

INSERT INTO devices_people (device_id, people_id) VALUES
    (1, 1), 
    (2, 2), 
    (3, 3), 
    (4, 4), 
    (2, 1), 
    (3, 2);
	
--3. Используя агрегатные функции вывести среднюю цену устройств.
select avg(price) as "avg price" from devices;

--4. Используя группировку вывести для каждого человека среднюю цену его устройств.
select p.name as person_name, avg(d.price) AS avg_device_price 
FROM people p
JOIN devices_people dp ON p.id = dp.people_id
JOIN devices d ON dp.device_id = d.id
GROUP BY p.name;

--5. Дополнить запрос п.4. условием, что средняя стоимость устройств должна быть больше 500.
select p.name as person_name, avg(d.price) AS avg_device_price 
FROM people p
JOIN devices_people dp ON p.id = dp.people_id
JOIN devices d ON dp.device_id = d.id
GROUP BY p.name
HAVING avg(d.price) > 500;