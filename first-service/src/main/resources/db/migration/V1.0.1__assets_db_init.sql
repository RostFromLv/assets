create schema if not exists public;

create table if not exists public.first_s(
    id           serial primary key,
    brand        VARCHAR(255),
    body_type    VARCHAR(255),
    wheel_radius DOUBLE PRECISION,
    deleted      boolean   default false,
    created_at   TIMESTAMP DEFAULT now(),
    updated_at   TIMESTAMP DEFAULT now()
);