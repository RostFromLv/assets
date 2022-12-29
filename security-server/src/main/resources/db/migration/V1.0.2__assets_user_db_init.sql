create table if not exists users
(
    id         serial primary key,
    name       VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    deleted    boolean   default false,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now()
);

-- GRANT ALL ON TABLE users TO asset;

CREATE INDEX users_id_index ON users (id);