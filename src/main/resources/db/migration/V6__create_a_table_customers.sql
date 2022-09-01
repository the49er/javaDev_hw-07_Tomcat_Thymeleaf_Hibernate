USE homework_7;
DROP TABLE IF EXISTS customers;
CREATE TABLE customers (
id BIGINT NOT NULL AUTO_INCREMENT,
name VARCHAR(100),
business_sphere VARCHAR(150),
PRIMARY KEY(id)
);

INSERT INTO customers (name, business_sphere)
VALUES
('Peri', 'Building Constructions'),
('Mail Express', 'Logistic Company'),
('MoneyBank', 'Banking'),
('BetStore', 'Gambling');


