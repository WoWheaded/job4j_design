CREATE TABLE accounts (
    id serial PRIMARY KEY,
    account_number varchar(255) UNIQUE NOT NULL,
    balance numeric(10, 2) NOT NULL
);

INSERT INTO accounts (account_number, balance)
VALUES
    ('12345', 1000.00),
    ('67890', 1500.00); 
	
begin transaction isolation level serializable;
UPDATE accounts SET balance = balance + 200.00 WHERE account_number = '12345';
commit;