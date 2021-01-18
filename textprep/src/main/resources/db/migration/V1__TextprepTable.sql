CREATE TABLE Users (
    id VARCHAR(100) NOT NULL PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    user_role VARCHAR(30) NOT NULL,
    locked boolean not NULL,
    enabled boolean NOT NULL
);

CREATE TABLE confirmation_token (
    id BIGSERIAL NOT NULL,
    confirmation_token varchar(100) not null,
    created_date VARCHAR(100) not null
);


CREATE TABLE Schemas (
    id VARCHAR(100) NOT NULL PRIMARY KEY,
    name varchar(100) not null,
    type VARCHAR(100) not null,
    user_id VARCHAR(100) NOT NULL,
    data bigint
);

CREATE TABLE Questions (
    id VARCHAR(100) NOT NULL PRIMARY KEY,
    number INTEGER not null,
    description VARCHAR(255) not null,
    schema_id VARCHAR(100) not null
);

CREATE TABLE Groups (
    id VARCHAR(100) NOT NULL PRIMARY KEY,
    name varchar(100),
    user_id VARCHAR(100)
);

CREATE TABLE Groups_Members (
    group_id VARCHAR(100),
    user_id VARCHAR(100)
);

CREATE TABLE Created_files (
    id VARCHAR(100) NOT NULL PRIMARY KEY,
    name varchar(100) not null,
    type VARCHAR(100) not null,
    user_id VARCHAR(100) NOT NULL,
    data bigint,
    date  varchar(100)
);

CREATE TABLE shared_schemas (
    id VARCHAR(100) NOT NULL PRIMARY KEY,
    SCHEMA_id varchar(100),
    group_id varchar(100),
    user_id varchar(100)
);


