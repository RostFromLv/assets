create table if not exists first_s(
    id           serial primary key,
    brand        VARCHAR(255),
    body_type    VARCHAR(255),
    wheel_radius DOUBLE PRECISION,
    deleted      boolean   default false,
    created_at   TIMESTAMP DEFAULT now(),
    updated_at   TIMESTAMP DEFAULT now()
);

GRANT ALL ON TABLE first_s TO asset;