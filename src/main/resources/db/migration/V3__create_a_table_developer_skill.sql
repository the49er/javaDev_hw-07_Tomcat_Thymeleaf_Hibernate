USE homework_7;
DROP TABLE IF EXISTS developer_skill;
CREATE TABLE developer_skill(
developer_id BIGINT,
skill_id BIGINT,
PRIMARY KEY (developer_id, skill_id),
FOREIGN KEY (developer_id) REFERENCES developers(id)
ON DELETE CASCADE,
FOREIGN KEY (skill_id) REFERENCES skills(id)
ON DELETE CASCADE
);



