CREATE TABLE user_cities (
        user_id         BIGINT NOT NULL,
        city_id         BIGINT NOT NULL,
        PRIMARY KEY (user_id, city_id),
        FOREIGN KEY (city_id) REFERENCES cities(id),
        FOREIGN KEY (user_id) REFERENCES users(id)
);