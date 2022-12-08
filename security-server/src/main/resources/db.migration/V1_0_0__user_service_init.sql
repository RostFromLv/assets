create table users
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(63)        NOT NULL,
    last_name  VARCHAR(63)        NOT NULL,
    email      VARCHAR(63) UNIQUE NOT NULL,
    password   VARCHAR(64)        NOT NULL,
    deleted    boolean   DEFAULT false,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now()
);