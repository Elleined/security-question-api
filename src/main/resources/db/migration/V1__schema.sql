CREATE TABLE IF NOT EXISTS security_question (
id CHAR(36) PRIMARY KEY,
created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
name VARCHAR(255) NOT NULL UNIQUE,
INDEX idx_name_and_created_at (name, created_at)
);

CREATE TABLE IF NOT EXISTS pre_defined_security_question (
id CHAR(36) PRIMARY KEY,
created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
resource_id CHAR(36) NOT NULL,
answer CHAR(60) NOT NULL,
security_question_id CHAR(36) NOT NULL,
FOREIGN KEY (security_question_id) REFERENCES security_question(id),
UNIQUE (resource_id, security_question_id),
INDEX idx_resource_id_and_created_at (resource_id, created_at)
);

CREATE TABLE IF NOT EXISTS user_defined_security_question (
id CHAR(36) PRIMARY KEY,
created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
resource_id CHAR(36) NOT NULL,
answer CHAR(60) NOT NULL,
question VARCHAR(255) NOT NULL,
INDEX idx_resource_id_and_created_at (resource_id, created_at)
);