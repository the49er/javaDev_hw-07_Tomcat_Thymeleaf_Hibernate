USE homework_7;
DROP TABLE IF EXISTS developers;
CREATE TABLE developers (
id BIGINT AUTO_INCREMENT,
name VARCHAR(100),
age INT,
gender VARCHAR(100),
salary INT,
company_id BIGINT NULL,
CONSTRAINT gender_enum_values
CHECK (gender IN ('male', 'female', 'other')),
PRIMARY KEY(id)
);




