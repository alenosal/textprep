CREATE TABLE Users (
    id_user UUID NOT NULL PRIMARY KEY,
    user_type_id integer,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    id_group UUID
);

CREATE TABLE Groups (
    id_group UUID not null PRIMARY KEY,
    name varchar(100) not null,
    id_user UUID NOT NULL
);

CREATE TABLE User_type (
    id_type integer,
    type_name VARCHAR(100)
);

INSERT into user_type (id_type, type_name) values (1, 'admin'), (2, 'user'), (3, 'group_admin');

CREATE TABLE Files (
    id VARCHAR(100) NOT NULL PRIMARY KEY,
    name varchar(100) not null,
    data bigint
);