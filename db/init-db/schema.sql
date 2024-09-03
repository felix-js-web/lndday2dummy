CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

INSERT INTO users (name) VALUES ('John Doe');
INSERT INTO users (name) VALUES ('Jane Doe');
