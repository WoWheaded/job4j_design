create table students(
	id serial primary key,
	name varchar(50),
	faculty text,
	public_education boolean
);

insert into students(name, faculty, public_education) 
	values('Dmitry', 'IT', true);
	
update students set name = 'DMITRY';

delete from students;

select * from students;
	