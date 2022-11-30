create table second_s(
    id         serial primary key,
    name       varchar(63),
    age        DOUBLE PRECISION,
    car_id     BIGINT,
    deleted    boolean   default false,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);