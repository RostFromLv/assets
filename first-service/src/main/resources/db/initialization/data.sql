-- first service
INSERT INTO public.first_s (id, brand, body_type, wheel_radius)
VALUES (1, 'Mersedes-Benz', 'sedan', 16);
INSERT INTO public.first_s (id, brand, body_type, wheel_radius)
VALUES (2, 'Audi', 'coupe', 17);
INSERT INTO public.first_s (id, brand, body_type, wheel_radius)
VALUES (3, 'Bmw', 'wagon', 16.5);

-- second service
INSERT INTO public.second_s(id, name, age, first_id)
VALUES (1,'Ivan', 23, 1);
INSERT INTO public.second_s(id, name, age, first_id)
VALUES (2,'Peter', 27, 2);
INSERT INTO public.second_s(id, name, age, first_id)
VALUES (3,'Ivan', 31, 3);