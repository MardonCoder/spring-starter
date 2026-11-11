--liquibase formatted sql

--changeset mardon:1
ALTER TABLE users
ADD COLUMN created_at TIMESTAMP;

ALTER TABLE users
ADD COLUMN updated_at TIMESTAMP;

ALTER TABLE users
ADD COLUMN created_by VARCHAR(32);

ALTER TABLE users
ADD COLUMN last_updated_by VARCHAR(32);

CREATE TABLE IF NOT EXISTS revision (
    id SERIAL PRIMARY KEY,
    timestamp BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS users_aud (
    id BIGINT NOT NULL,
    rev INTEGER NOT NULL,
    revtype SMALLINT,

    username VARCHAR(255),
    birth_date DATE,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    role VARCHAR(255),
    company_id BIGINT,

    created_at TIMESTAMP WITH TIME ZONE,
    updated_at TIMESTAMP WITH TIME ZONE,
    created_by VARCHAR(255),
    last_updated_by VARCHAR(255),

    CONSTRAINT pk_users_aud PRIMARY KEY (id, rev),
    CONSTRAINT fk_users_aud_rev FOREIGN KEY (rev) REFERENCES revision (id)
);

CREATE INDEX IF NOT EXISTS idx_users_aud_id ON users_aud (id);