USE homework_7;
ALTER TABLE projects
ADD CONSTRAINT company_id_fk
FOREIGN KEY (company_id)
REFERENCES companies(id)
ON DELETE CASCADE;


