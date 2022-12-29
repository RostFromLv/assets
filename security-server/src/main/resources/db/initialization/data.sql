-- roles insert
INSERT INTO public.role(id, role_name)
VALUES (1, 'ROLE_ADMIN')
ON CONFLICT DO NOTHING;
INSERT INTO public.role(id, role_name)
VALUES (2, 'ROLE_VIP')
ON CONFLICT DO NOTHING;
INSERT INTO public.role(id, role_name)
VALUES (3, 'ROLE_USER')
ON CONFLICT DO NOTHING;

-- alter sequence public.role_id_seq restart with 4;

-- user insert
INSERT INTO public.users(id, name, last_name, email, password)
VALUES (1, 'Ruslan', 'Sudak', 'first@email.com', '$2a$15$MIU/M.Kzu/8RC2s3pqhSg.mnJQv87we0MSObDhX7XBk/vnPnRy4qO')
-- password1
ON CONFLICT DO NOTHING;
INSERT INTO public.users(id, name, last_name, email, password)
VALUES (2, 'Peter', 'Ivanechko', 'second@email.com', '$2y$15$xGyD4K3LnUTq4gwcoaL8KeZczm94iQWvMkV2p/2l4jkvd/RDcy2Xe')
ON CONFLICT DO NOTHING;
INSERT INTO public.users(id, name, last_name, email, password)
VALUES (3, 'Ivan', 'Vuhan', 'third@email.com', '$2y$15$35lo7U/LRvHZvPz/EMYhFuasKhVbrueLD2rEX0sNtlrvNpUjUTe6q')
ON CONFLICT DO NOTHING;

-- alter sequence public.users_id_seq restart with 4;


INSERT INTO public.users_role(role_id, user_id)
VALUES (1, 3) ON CONFLICT DO NOTHING;
INSERT INTO public.users_role(role_id, user_id)
VALUES (3, 2) ON CONFLICT DO NOTHING;
INSERT INTO public.users_role(role_id, user_id)
VALUES (3, 1) ON CONFLICT DO NOTHING;

-- alter sequence public.users_role_id_seq restart with 4;