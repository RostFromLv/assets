create table if not exists public.second_s
(
    id         serial primary key,
    name       varchar(255),
    age        DOUBLE PRECISION,
    first_id   BIGINT,
    deleted    boolean   default false,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);