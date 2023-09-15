CREATE TABLE accounts (
    id serial PRIMARY KEY,
    account_number varchar(255) UNIQUE NOT NULL,
    balance numeric(10, 2) NOT NULL
);

INSERT INTO accounts (account_number, balance)
VALUES
    ('12345', 1000.00),
    ('67890', 1500.00); 
	
begin;
savepoint savepoint1;
UPDATE accounts SET balance = 0 WHERE id = 1;
savepoint savepoint2;
UPDATE accounts SET balance = 0 WHERE id = 2;
select * from accounts;
ROLLBACK TO savepoint2;
select * from accounts;
ROLLBACK TO savepoint1;
select * from accounts;
UPDATE accounts SET balance = 0 WHERE id = 2;
commit;
select * from accounts;