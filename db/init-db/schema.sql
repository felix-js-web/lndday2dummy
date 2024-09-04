CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

INSERT INTO users (name) VALUES ('John Doe');
INSERT INTO users (name) VALUES ('Jane Doe2');
INSERT INTO users (name) VALUES ('Jane Doe3');

CREATE TABLE balance (
    id VARCHAR(50) NOT NULL,
    transactionId VARCHAR(50) NOT NULL,
    amount BIGINT NOT NULL,
    version INT NOT NULL CHECK (version > 0),
    balance BIGINT NOT NULL CHECK (balance >= 0),
    createdDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id, version),
    CONSTRAINT unique_transaction_id UNIQUE (transactionId, id)
);

INSERT INTO balance (id, transactionId, amount, version, balance, createdDate) VALUES
('wallet1', 'txn1', 1000, 1, 1000, '2023-01-01 10:00:00'),
('wallet1', 'txn2', 500, 2, 1500, '2023-01-02 10:00:00'),
('wallet1', 'txn3', -200, 3, 1300, '2023-01-03 10:00:00'),
('wallet2', 'txn4', 2000, 1, 2000, '2023-01-01 11:00:00'),
('wallet2', 'txn5', -500, 2, 1500, '2023-01-02 11:00:00'),
('wallet2', 'txn6', 300, 3, 1800, '2023-01-03 11:00:00'),
('wallet3', 'txn7', 1500, 1, 1500, '2023-01-01 12:00:00'),
('wallet3', 'txn8', 700, 2, 2200, '2023-01-02 12:00:00'),
('wallet3', 'txn9', -300, 3, 1900, '2023-01-03 12:00:00');