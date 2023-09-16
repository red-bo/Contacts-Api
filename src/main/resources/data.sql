create table users
(
    id       bigserial
        primary key,
    username varchar(255)
        unique,
    password varchar(255)
        unique
);