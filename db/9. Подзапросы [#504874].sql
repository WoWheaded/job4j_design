CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

INSERT INTO customers (first_name, last_name, age, country)
VALUES
    ('John', 'Doe', 25, 'USA'),
    ('Alice', 'Smith', 30, 'Canada'),
    ('Michael', 'Johnson', 22, 'Australia'),
    ('Emily', 'Brown', 19, 'UK');
--Выполните запрос, который вернет список клиентов, возраст которых является минимальным.	
select * from customers where age = (select min(age) from customers);

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

INSERT INTO orders (amount, customer_id)
VALUES
    (100, 1), 
    (200, 2),  
    (150, 3),  
    (120, 1);
	
--выполнить запрос, который вернет список пользователей, которые еще не выполнили ни одного заказа
select * from customers
where id not in (select DISTINCT customer_id from orders);