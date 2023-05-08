CREATE TABLE user_subscribe_city (
         user_id         BIGINT NOT NULL,
         city_id         BIGINT NOT NULL,
         PRIMARY KEY (city_id, user_id),
         FOREIGN KEY (city_id) REFERENCES cities(id),
         FOREIGN KEY (user_id) REFERENCES users(id)
);