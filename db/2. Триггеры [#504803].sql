create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

--1)  Триггер должен срабатывать после вставки данных, для любого товара и просто насчитывать налог на товар (нужно прибавить налог к цене товара).
--Действовать он должен не на каждый ряд, а на запрос (statement уровень)
create or replace function tax_after_insert()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id in (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger_after_insert
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax_after_insert();

-- 2) Триггер должен срабатывать до вставки данных и насчитывать налог на товар (нужно прибавить налог к цене товара). 
--Здесь используем row уровень.
create or replace function tax_before_insert()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_before_insert_trigger
    before insert
    on products
    for each row
    execute procedure tax_before_insert();

--        Нужно написать триггер на row уровне, который сразу после вставки продукта в таблицу products, будет заносить имя, цену и текущую дату в таблицу history_of_price. 
create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function add_history_of_price()
    returns trigger as
$$
    BEGIN
        insert into history_of_price(name, price, date)
		values(new.name, new.price, current_date);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger add_history_of_price_trigger
	after insert on products
	for each row
	execute procedure add_history_of_price();