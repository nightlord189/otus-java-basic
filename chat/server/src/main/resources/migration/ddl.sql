create extension if not exists pgcrypto;

do $$
begin
    if not exists (select 1 from pg_type where typname = 'user_role') then
        create type user_role as enum ('USER', 'ADMIN');
    end if;
end $$;

create table if not exists users (
    login varchar (255) primary key,
    password_hash varchar (255) not null,
    role user_role not null default 'USER'
);

insert into users (login, password_hash, role) values
    ('admin1', encode(digest('password1', 'sha256'), 'hex'), 'ADMIN'),
    ('qwe', encode(digest('qwe', 'sha256'), 'hex'), 'USER'),
    ('asd', encode(digest('asd', 'sha256'), 'hex'), 'USER')
    ('zxc', encode(digest('zxc', 'sha256'), 'hex'), 'USER')
on conflict (login) do nothing;