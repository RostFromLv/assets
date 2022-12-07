create table users
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(63)        NOT NULL,
    last_name  VARCHAR(63)        NOT NULL,
    email      VARCHAR(63) UNIQUE NOT NULL,
    password   VARCHAR(64)        NOT NULL,
    deleted    boolean   DEFAULT false,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT UQ_users_name_lastName UNIQUE (name, last_name)
);