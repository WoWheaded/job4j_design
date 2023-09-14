insert into roles(name) values ('ADMIN'), ('USER');

insert into users(username, roles_id ) values ('Dmitry', 1);

insert into rules(rule_name) values ('UPDATE'), ('DELETE');

insert into roles_rules(role_id, rule_ud) values(1, 1);
insert into roles_rules(role_id, rule_ud) values(1, 2);
insert into roles_rules(role_id, rule_ud) values(2, 1);

insert into categories(category_name) values
    ('Bug Report'),
    ('Feature Request'),
    ('Technical Support'); 

insert into states (state_name) values
    ('Open'),
    ('In Progress'),
    ('Closed');
	
	
insert into items (title,
	description, 
	users_id,
	categories_id, 
	states_id) values(
		'Компуктер не включается',
		'После установки дров перестал включаться',
		1,
		3,
		1);

insert into comments(comment_text, item_id) values(
	'компьютер старенький',
	1);
	
insert into attachs(file_name, item_id) values('video.mp4', 1);