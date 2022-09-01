USE homework_7;
DROP TABLE IF EXISTS company_customer;
CREATE TABLE IF NOT EXISTS company_customer (
company_id BIGINT NULL,
customer_id BIGINT NULL,
UNIQUE (company_id, customer_id),
FOREIGN KEY (company_id) REFERENCES companies(id)
ON DELETE CASCADE,
FOREIGN KEY (customer_id) REFERENCES customers(id)
ON DELETE CASCADE
);




