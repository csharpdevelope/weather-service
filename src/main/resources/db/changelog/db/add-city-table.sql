CREATE TABLE cities(
        id              BIGSERIAL PRIMARY KEY NOT NULL,
        create_date     TIMESTAMP NOT NULL,
        update_date     TIMESTAMP NOT NULL,
        active          BOOLEAN NOT NULL,
        name            VARCHAR(150),
        latitude        VARCHAR(60),
        longitude       VARCHAR(60),
        country         VARCHAR(50),
        user_id         BIGINT,
        FOREIGN KEY (id) REFERENCES users(id)
);