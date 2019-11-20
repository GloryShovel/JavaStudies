CREATE SEQUENCE hibernate_sequence;

CREATE TABLE profile
(
    id      BIGSERIAL NOT NULL,
    name    VARCHAR   NOT NULL,

    PRIMARY KEY (id)
);