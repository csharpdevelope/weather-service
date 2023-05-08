ALTER TABLE cities
    ADD CONSTRAINT fk_weather_id
    FOREIGN KEY (weather_id) REFERENCES weathers(id);