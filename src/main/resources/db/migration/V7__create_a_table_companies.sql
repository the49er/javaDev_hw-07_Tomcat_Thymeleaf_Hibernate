USE homework_7;
DROP TABLE IF EXISTS companies;
CREATE TABLE companies (
id BIGINT NOT NULL AUTO_INCREMENT,
name VARCHAR(100),
specialization VARCHAR(150),
PRIMARY KEY (id)
);
