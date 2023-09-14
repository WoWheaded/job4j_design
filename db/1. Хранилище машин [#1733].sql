CREATE TABLE car_bodies (
    id serial PRIMARY KEY,
    name varchar(255) NOT NULL
);

CREATE TABLE car_engines (
    id serial PRIMARY KEY,
    name varchar(255) NOT NULL
);

CREATE TABLE car_transmissions (
    id serial PRIMARY KEY,
    name varchar(255) NOT NULL
);

CREATE TABLE cars (
    id serial PRIMARY KEY,
    name varchar(255) NOT NULL,
    body_id int REFERENCES car_bodies(id),
    engine_id int REFERENCES car_engines(id),
    transmission_id int REFERENCES car_transmissions(id)
);

INSERT INTO car_transmissions (name) VALUES
    ('Manual'),
    ('Automatic'),
    ('CVT'),
    ('Dual Clutch'),
	('Legs');

INSERT INTO car_engines (name) VALUES
    ('V4'),
    ('V6'),
    ('V8'),
    ('Electric'),
	('V10');

INSERT INTO car_bodies (name) VALUES
    ('Sedan'),
    ('SUV'),
    ('Hatchback'),
    ('Crossover'),
	('Sport');
	
INSERT INTO cars (name, body_id, engine_id, transmission_id) VALUES
    ('Car1', 1, 2, 3),
    ('Car2', 2, 1, 2),
    ('Car3', 3, 3, 4),
    ('Car4', 4, 4, 1),
	('Car5', null, 2, 3);
	
--Вывести список всех машин и все привязанные к ним детали. 	
select c.id,
	c.name as car_name, 
	cb.name as body_name, 
	ce.name as engine_name, 
	ct.name as transmission_name
from cars c
left join car_bodies cb on c.body_id = cb.id
left join car_engines ce on c.engine_id = ce.id
left join car_transmissions ct on c.transmission_id = ct.id

--Вывести кузова, которые не используются НИ в одной машине.
select cb.name AS unused_body
from car_bodies cb
left join cars c on c.body_id = cb.id
where c.id is null;

--Вывести двигатели, которые не используются НИ в одной машине
select ce.name AS unused_engine
from car_engines ce
left join cars c on c.engine_id = ce.id
where c.id is null;

--Вывести коробки передач, которые не используются НИ в одной машине
select ct.name AS unused_transmission
from car_transmissions ct
left join cars c on c.transmission_id = ct.id
where c.id is null;