CREATE TABLE users (
       id              BIGSERIAL PRIMARY KEY NOT NULL,
       create_date     TIMESTAMP NOT NULL,
       update_date     TIMESTAMP NOT NULL,
       active          BOOLEAN NOT NULL,
       username        VARCHAR(255) NOT NULL,
       password        VARCHAR(255) NOT NULL,
       firstname       VARCHAR(255),
       lastname        VARCHAR(255)
);

CREATE TABLE roles (
    id        BIGSERIAL PRIMARY KEY NOT NULL,
    name      VARCHAR(50) NOT NULL
);

CREATE TABLE user_roles (
       user_id         BIGINT NOT NULL,
       role_id         BIGINT NOT NULL,
       PRIMARY KEY (user_id, role_id),
       FOREIGN KEY (role_id) REFERENCES roles(id),
       FOREIGN KEY (user_id) REFERENCES users(id)
);
