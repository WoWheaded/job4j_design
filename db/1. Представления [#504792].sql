CREATE TABLE orders (
    order_id serial PRIMARY KEY,
    customer_name varchar(255) NOT NULL,
    order_date date NOT NULL
);

CREATE TABLE products (
    product_id serial PRIMARY KEY,
    product_name varchar(255) NOT NULL,
    category varchar(255) NOT NULL
);

CREATE TABLE order_items (
    order_item_id serial PRIMARY KEY,
    order_id int REFERENCES orders(order_id),
    product_name varchar(255) NOT NULL,
    quantity int NOT NULL,
    price decimal(10, 2) NOT NULL
);

INSERT INTO orders (customer_name, order_date) VALUES
    ('John Smith', '2023-01-15'),
    ('Alice Johnson', '2023-01-16'),
    ('Bob Brown', '2023-01-17');

INSERT INTO products (product_name, category) VALUES
    ('Laptop', 'Electronics'),
    ('Smartphone', 'Electronics'),
    ('Chair', 'Furniture'),
    ('Tablet', 'Electronics'),
    ('Sofa', 'Furniture');

INSERT INTO order_items (order_id, product_name, quantity, price) VALUES
    (1, 'Laptop', 2, 1200.00),
    (1, 'Smartphone', 3, 800.00),
    (2, 'Chair', 4, 50.00),
    (3, 'Tablet', 1, 600.00),
    (3, 'Sofa', 1, 800.00);
	
--обычнй запрос
SELECT o.order_id, o.customer_name, o.order_date, oi.product_name, oi.quantity, oi.price
FROM orders o
JOIN order_items oi ON o.order_id = oi.order_id
JOIN products p ON oi.product_name = p.product_name;

CREATE VIEW order_details AS
SELECT o.order_id, o.customer_name, o.order_date, oi.product_name, oi.quantity, oi.price
FROM orders o
JOIN order_items oi ON o.order_id = oi.order_id
JOIN products p ON oi.product_name = p.product_name;

SELECT * FROM order_details;