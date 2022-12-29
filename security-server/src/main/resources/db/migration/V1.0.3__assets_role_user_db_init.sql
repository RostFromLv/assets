create table if not exists users_role
(
    user_id BIGINT REFERENCES users (id) NOT NULL,
    role_id BIGINT REFERENCES role (id)  NOT NULL,

    CONSTRAINT PK_user_role_ids PRIMARY KEY (user_id,role_id),
    CONSTRAINT UQ_user_id_role_id UNIQUE(user_id,role_id)
);

-- GRANT  ALL ON TABLE user_role TO asset