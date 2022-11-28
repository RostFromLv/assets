create table second(
    id         serial primary key,
    name       varchar(63),
    age        DOUBLE PRECISION,
    car_id     BIGINT,
    deleted    boolean   default false,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO second(name, age, car_id)
VALUES ('Ivan', 23, 1);
INSERT INTO second(name, age, car_id)
VALUES ('Peter', 27, 2);
INSERT INTO second(name, age, car_id)
VALUES ('Ivan', 31, 3);