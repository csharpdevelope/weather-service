CREATE TABLE weathers (
        id              BIGSERIAL PRIMARY KEY NOT NULL,
        create_date     TIMESTAMP NOT NULL,
        update_date     TIMESTAMP NOT NULL,
        active          BOOLEAN NOT NULL,
        city_name       VARCHAR(150) NOT NULL,
        country_code    VARCHAR(50) NOT NULL,
        temperature     VARCHAR(50) NOT NULL,
        now_date        VARCHAR NOT NULL,
        weather_main    VARCHAR(150),
        city_id         BIGINT,
        FOREIGN KEY (id) REFERENCES cities(id)
);