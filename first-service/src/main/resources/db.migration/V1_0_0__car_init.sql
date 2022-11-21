create table cars(
    id           serial primary key,
    brand        VARCHAR(63),
    body_type    VARCHAR(63),
    wheel_radius DOUBLE PRECISION,
    deleted      boolean   default false,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO cars (brand, body_type, wheel_radius)
VALUES ('Mersedes-Benz', 'sedan', 16);
INSERT INTO cars (brand, body_type, wheel_radius)
VALUES ('Audi', 'coupe', 17);
INSERT INTO cars (brand, body_type, wheel_radius)
VALUES ('Bmw', 'wagon', 16.5);