CREATE TABLE roles (
    id serial PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE users (
    id serial PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    roles_id int references roles(id)
);

CREATE TABLE rules (
    id serial PRIMARY KEY,
    rule_name VARCHAR(255) NOT NULL
);

create table roles_rules(
	id serial PRIMARY KEY,
	role_id int references roles(id),
	rule_ud int references rules(id)
);

CREATE TABLE categories (
    id serial PRIMARY KEY,
    category_name VARCHAR(255)
);

CREATE TABLE states (
    id serial PRIMARY KEY,
    state_name VARCHAR(255)
);

CREATE TABLE items (
    id serial PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
	users_id int references users(id),
	categories_id int references categories(id),
	states_id int references states(id)
);

CREATE TABLE comments (
    id serial PRIMARY KEY,
	comment_text varchar(255),
	item_id int references items(id)
);

CREATE TABLE attachs (
    id serial PRIMARY KEY,
	file_name varchar(70),
	item_id int references items(id)
);