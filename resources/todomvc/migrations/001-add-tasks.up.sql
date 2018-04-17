create table tasks (
    id serial primary key,
    title text not null,
    description text,
    done boolean default false not null)
