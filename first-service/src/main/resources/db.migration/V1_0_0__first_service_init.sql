create table first_s(
    id           serial primary key,
    brand        VARCHAR(63),
    body_type    VARCHAR(63),
    wheel_radius DOUBLE PRECISION,
    deleted      boolean   default false,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);