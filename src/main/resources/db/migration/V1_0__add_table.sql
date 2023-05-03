CREATE SCHEMA IF NOT EXISTS social;

CREATE TABLE IF NOT EXISTS social.users
(
    id          varchar PRIMARY KEY NOT NULL,
    first_name  varchar             NOT NULL,
    second_name varchar             NOT NULL,
    age         integer             NOT NULL,
    birthdate   date                NOT NULL,
    biography   varchar,
    city        varchar,
    password    varchar             NOT NULL

);

CREATE TABLE IF NOT EXISTS social.session
(
    id         serial PRIMARY KEY NOT NULL,
    user_id    varchar UNIQUE     NOT NULL,
    token      varchar            NOT NULL,
    created_at timestamp          NOT NULL
);