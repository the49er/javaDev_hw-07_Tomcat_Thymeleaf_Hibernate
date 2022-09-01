USE homework_7;
ALTER TABLE developers
ADD CONSTRAINT employer_company_id_fk
FOREIGN KEY (company_id)
REFERENCES companies(id)
ON DELETE CASCADE;


