USE homework_7;
CREATE TABLE IF NOT EXISTS projects (
id BIGINT NOT NULL AUTO_INCREMENT,
name VARCHAR(100),
description VARCHAR(150),
date_contract DATE,
customer_id BIGINT NULL,
company_id BIGINT NULL,
PRIMARY KEY(id)
);



