CREATE TABLE departments (
    id serial PRIMARY KEY,
    name varchar(255) NOT NULL
);


CREATE TABLE employees (
    id serial PRIMARY KEY,
    name varchar(255) NOT NULL,
    department_id int REFERENCES departments(id)
);

INSERT INTO departments (name) VALUES
    ('HR'),
    ('IT'),
    ('Sales'),
    ('Marketing'),
	('Banking');
	
INSERT INTO employees (name, department_id) VALUES
    ('John Smith', 1),    
    ('Alice Johnson', 2),  
    ('Bob Brown', 3),     
    ('Eva Davis', 4),     
    ('Michael Clark', 2),
	('Dmitry Arinin', null);

--3. Используя left join найти департаменты, у которых нет работников
select d.name
from departments d
left outer join employees e on e.department_id = d.id
where e.id is null;

--4. Используя left и right join написать запросы, которые давали бы одинаковый результат
SELECT d.name AS department_name, e.name AS employee_name
FROM departments d
LEFT JOIN employees e ON d.id = e.department_id;

SELECT d.name AS department_name, e.name AS employee_name
FROM employees e
RIGHT JOIN departments d ON d.id = e.department_id;

--5. Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join составить все возможные разнополые пары
CREATE TABLE teens (
    id serial PRIMARY KEY,
    name varchar(255),
    gender varchar(10)
);

INSERT INTO teens (name, gender) VALUES
    ('Alice', 'female'),
    ('Bob', 'male'),
    ('Charlie', 'male'),
    ('David', 'male'),
    ('Eva', 'female');
	
SELECT t1.name AS teen1, t2.name AS teen2
FROM teens t1
CROSS JOIN teens t2
WHERE t1.gender <> t2.gender;