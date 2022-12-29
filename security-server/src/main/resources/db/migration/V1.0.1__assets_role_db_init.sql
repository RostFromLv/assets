create table if not exists role
(
    id         SERIAL primary key,
    role_name  varchar(255) NOT NULL,
    deleted    boolean   default false,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now()
);

-- GRANT ALL ON TABLE role TO asset;