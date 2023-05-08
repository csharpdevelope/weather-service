package com.example.weather.component;

import com.example.weather.model.entity.City;
import com.example.weather.repository.CityRepository;
import com.example.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyCommandLineRunner implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final WeatherService weatherService;
    private final CityRepository cityRepository;
    @Override
    public void run(String... args) {
        logger.info("Read Weather Data....");
//        City city = cityRepository.findByName("Margilan");
//        weatherService.changeWeather(city);
    }
}
