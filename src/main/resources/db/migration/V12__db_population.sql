USE homework_7;

INSERT INTO companies (name, specialization) VALUES
('Game Soft', 'Game Software'),
('iNet Security','iNet Security'),
('Global Link','Networks'),
('Soft Group','Enterprise Solutions');


INSERT INTO developers (name, age, gender, salary, company_id) VALUES
('Vitaly', 25, 'MALE', 3600, 1),
('Irina', 31, 'FEMALE', 3450, 2),
('Sabrina', 29, 'OTHER', 4750, 3),
('Yehor', 19, 'MALE', 4100, 4),
('Sergiy', 42, 'MALE', 4250, 1),
('Olena', 25, 'OTHER', 2100, 2),
('Oksana', 43, 'FEMALE', 4200, 3),
('John', 35, 'MALE', 3600, 4),
('Dmytro', 22, 'MALE', 5500, 1),
('Helga', 22, 'FEMALE', 2000, 2);

INSERT INTO customers (name, business_sphere) VALUES
('Peri', 'Building Constructions'),
('Mail Express', 'Logistic Company'),
('MoneyBank', 'Banking'),
('BetStore', 'Gambling');


INSERT INTO projects (name, description, date_contract, customer_id, company_id) VALUES
('CRM', 'Customer Relationship Management', '2021-01-24', 1, 4),
('ERP', 'Enterprise Resource Planning', '2021-04-12', 2, 3),
('SCM', 'Software Configuration Management', '2019-05-19', 3, 2),
('HMC', 'Human Capital Management', '2019-05-19', 4, 1);

INSERT INTO skills (programming_lang, level) VALUES
('Java', 'Junior'),
('Java', 'Middle'),
('Java', 'Senior'),
('C++', 'Junior'),
('C++', 'Middle'),
('C++', 'Senior'),
('C#', 'Junior'),
('C#', 'Middle'),
('C#', 'Senior'),
('JavaScript', 'Junior'),
('JavaScript', 'Middle'),
('JavaScript', 'Senior');

INSERT INTO company_customer (company_id, customer_id)
VALUES
(1, 4),
(2, 3),
(3, 1),
(4, 2);

INSERT INTO developer_skill (developer_id, skill_id)
VALUES
(3, 3),
(3, 6),
(4, 2),
(4, 5),
(1, 1),
(1, 4),
(2, 7),
(2, 12),
(5, 12),
(5, 9),
(6, 11),
(7, 6),
(7, 2),
(8, 10),
(9, 9),
(10, 10);






