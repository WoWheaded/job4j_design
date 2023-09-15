create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

--процедура для удаления по id
create or replace procedure delete_data_by_id(d_id integer)
language 'plpgsql'
as $$
    BEGIN
		delete from products where id = d_id;
    END
$$;

insert into products (name, producer, count, price) values ('product_1', 'producer_1', 3, 50);
insert into products (name, producer, count, price) values ('product_3', 'producer_3', 8, 115);
select * from products;

call delete_data_by_id(2);

delete from products;
ALTER SEQUENCE products_id_seq RESTART WITH 1;

--функция для удаления по полю count
create or replace function f_delete_data_by_count(i_count integer)
returns integer
language 'plpgsql'
as
$$
	declare
			result integer;
    begin
		select into result count from products where count < i_count;
       	delete from products where count < i_count;
	   	return result;
    end;
$$;

select f_delete_data_by_count(4);
select * from products;