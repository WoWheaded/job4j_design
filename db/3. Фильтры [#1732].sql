CREATE TABLE type (
    id serial primary key,
    name varchar(255)
);

CREATE TABLE product (
    id serial primary key,
    name varchar(255),
    type_id int references type(id),
    expired_date date,
    price float
);

INSERT INTO type (name) VALUES
    ('СЫР'),
    ('МОЛОКО'),
    ('МОРОЖЕНОЕ'),
    ('ХЛЕБ'),
    ('ОВОЩИ');

INSERT INTO product (name, type_id, expired_date, price) VALUES
    ('Гауда', 1, '2023-05-31', 5.99),
    ('Молоко пастеризованное', 2, '2023-09-30', 1.99),
    ('Ванильное мороженое', 3, '2023-10-15', 4.49),
    ('Черный хлеб', 4, '2023-09-25', 2.49),
    ('Помидоры', 5, '2023-09-20', 0.79);

--1. Написать запрос получение всех продуктов с типом "СЫР"
select p.* from product p
join type t on p.type_id = t.id
where t.name = 'СЫР';

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"
SELECT *
FROM product
WHERE name LIKE '%мороженое%';

--3. Написать запрос, который выводит все продукты, срок годности которых уже истек
SELECT *
FROM product
WHERE expired_date < current_date;

--4. Написать запрос, который выводит самый дорогой продукт. Запрос должен быть универсальный и находить все продукты с максимальной ценой.
SELECT *
FROM product
WHERE price = (SELECT MAX(price) FROM product);

--5. Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих. В виде имя_типа, количество
select t.name as "имя_типа", COUNT(p.id) as количество
from type t
join product p on p.type_id = t.id
group by t.name;

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select p.* 
from product p
join type t on p.type_id = t.id
WHERE t.name IN ('СЫР', 'МОЛОКО');

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
select t.name as продукт, COUNT(p.id) as количесвто
from type t
join product p on p.type_id = t.id
group by t.name
HAVING COUNT(p.id) < 10;

--8. Вывести все продукты и их тип.
select p.name продукты, t.name as тип
from product p
join type t on p.type_id = t.id;