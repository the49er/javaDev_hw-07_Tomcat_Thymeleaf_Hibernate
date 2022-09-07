USE homework_7;
CREATE TABLE IF NOT EXISTS developer_project (
developer_id BIGINT NULL,
project_id BIGINT NULL,
UNIQUE (developer_id, project_id),
FOREIGN KEY (developer_id) REFERENCES developers(id)
ON DELETE CASCADE,
FOREIGN KEY (project_id) REFERENCES projects(id)
ON DELETE CASCADE
);



