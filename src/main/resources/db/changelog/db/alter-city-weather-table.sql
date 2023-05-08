ALTER TABLE weathers
    ADD COLUMN city_id BIGINT,
    ADD FOREIGN KEY (city_id) REFERENCES cities(id);