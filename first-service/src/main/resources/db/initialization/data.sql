-- first service
INSERT INTO public.first_s (id, brand, body_type, wheel_radius)
VALUES (1, 'Mersedes-Benz', 'sedan', 16) on conflict (id) do nothing;

INSERT INTO public.first_s (id, brand, body_type, wheel_radius)
VALUES (2, 'Audi', 'coupe', 17) on conflict (id) do nothing;

INSERT INTO public.first_s (id, brand, body_type, wheel_radius)
VALUES (3, 'Bmw', 'wagon', 16.5) on conflict (id) do nothing;

alter sequence public.first_s_id_seq restart with 4;

-- second service
INSERT INTO public.second_s(id, name, age, first_id)
VALUES (1,'Ivan', 23, 1) on conflict (id) do nothing;

INSERT INTO public.second_s(id, name, age, first_id)
VALUES (2,'Peter', 27, 2) on conflict (id) do nothing;

INSERT INTO public.second_s(id, name, age, first_id)
VALUES (3,'Ivan', 31, 3) on conflict (id) do nothing;

alter sequence public.second_s_id_seq restart with 4;