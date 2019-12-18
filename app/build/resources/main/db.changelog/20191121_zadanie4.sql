CREATE TABLE branch(
    id              int NOT NULL PRIMARY KEY,
    name            varchar NOT NULL
);

CREATE TABLE category(
    id              int NOT NULL PRIMARY KEY,
    branch_id       int NOT NULL,

    name varchar NOT NULL,

    FOREIGN KEY (branch_id)     REFERENCES branch(id)
);

CREATE TABLE auction(
    id              int NOT NULL PRIMARY KEY,
    category_id     int NOT NULL,
    owner_id        int NOT NULL,

    title           varchar NOT NULL,
    description     varchar NOT NULL,
    price           float NOT NULL,


    FOREIGN KEY (category_id)   REFERENCES category(id),
    FOREIGN KEY (owner_id)      REFERENCES profile(id)
);

CREATE TABLE photos(
    id              int NOT NULL PRIMARY KEY,
    auction_id      int NOT NULL,

    link            varchar NOT NULL,

    FOREIGN KEY (auction_id)     REFERENCES auction(id)
);

CREATE TABLE parameters(
    id              int NOT NULL PRIMARY KEY,
    name            varchar NOT NULL,
    order_by        int NOT NULL
);

CREATE TABLE linker_auction_params(
--     id              int NOT NULL PRIMARY KEY,
    auction_id      int NOT NULL,
    parameter_id    int NOT NULL,

    value           varchar NOT NULL,

    PRIMARY KEY (auction_id, parameter_id),

    FOREIGN KEY (auction_id)     REFERENCES auction(id),
    FOREIGN KEY (parameter_id)   REFERENCES parameters(id)
);