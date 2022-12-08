create table if not exists second_s
(
    id         serial primary key,
    name       varchar(255),
    age        DOUBLE PRECISION,
    first_id   BIGINT,
    deleted    boolean   default false,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now()
);

GRANT ALL ON TABLE second_s TO asset;