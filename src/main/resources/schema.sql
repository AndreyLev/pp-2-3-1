DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id bigint generated always as identity,
    name varchar(255),
    last_name varchar(255),
    age tinyint,
    primary key (id)
);
