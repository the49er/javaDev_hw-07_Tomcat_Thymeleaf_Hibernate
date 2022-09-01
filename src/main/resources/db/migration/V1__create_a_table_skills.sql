USE homework_7;

DROP TABLE IF EXISTS skills;
CREATE TABLE skills (
id BIGINT AUTO_INCREMENT NOT NULL,
programming_lang VARCHAR(50) NOT NULL,
level VARCHAR(50) NOT NULL,
CONSTRAINT level_enum_values
CHECK (level IN ('Junior', 'Middle', 'Senior')),
CONSTRAINT lang_enum_values
CHECK (programming_lang IN ('C#', 'Java', 'JavaScript', 'C++')),
PRIMARY KEY(id)
);



