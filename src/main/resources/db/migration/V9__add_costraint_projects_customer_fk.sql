USE homework_7;
ALTER TABLE projects
ADD CONSTRAINT customer_id_fk
FOREIGN KEY (customer_id)
REFERENCES customers(id)
ON DELETE CASCADE;


