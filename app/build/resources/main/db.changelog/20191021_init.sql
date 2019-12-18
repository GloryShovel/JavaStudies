CREATE SEQUENCE hibernate_sequence;

CREATE TABLE profile
(
    id      BIGSERIAL NOT NULL,
    name    VARCHAR   NOT NULL,
    sname   VARCHAR   NOT NULL,
    login   VARCHAR   NOT NULL,
    pswrd   VARCHAR   NOT NULL,
    email   VARCHAR   NOT NULL,
    bday    VARCHAR   NOT NULL,

    PRIMARY KEY (id)
);