package com.example.weather.service;

import com.example.weather.model.entity.City;
import com.example.weather.model.entity.Weather;
import com.example.weather.repository.CityRepository;
import com.example.weather.repository.WeatherRepository;
import com.example.weather.utils.DateUtils;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final Logger logger = LoggerFactory.getLogger(WeatherService.class);
    private final HttpService httpService;
    private final CityRepository cityRepository;

    public void changeWeather(City city) {
        Weather weather = city.getWeather();
        if (weather == null) {
            weather = new Weather();
        }
        JsonNode jsonNode = requestWeather(city.getName());
        weather = setWeather(jsonNode, weather);
        weather.setActive(true);
        city.setWeather(weather);
        cityRepository.save(city);
        logger.info("Weather save up");
    }

    private JsonNode requestWeather(String city) {
        return httpService.requestService(city);
    }

    private Weather setWeather(JsonNode jsonNode, Weather weather) {
        if (jsonNode != null) {
            weather.setCountry(getValue(jsonNode.get("location"), "name"));
            weather.setTemperature(getValue(jsonNode.get("current"), "temp_c"));
            weather.setCountryCode("");
            weather.setCurrentDate(DateUtils.patternDate(getValue(jsonNode.get("current"), "last_updated")));
            weather.setWeatherMain(getValue(getJsonNodeInJson(jsonNode.get("current"), "condition"), "text"));
        }
        return weather;
    }

    private String getValue(JsonNode json, String name) {
        if (json == null) return null;
        return json.hasNonNull(name) ? json.get(name).asText() : null;
    }

    private JsonNode getJsonNodeInJson(JsonNode json, String jsonName) {
        if (json == null) return null;
        return json.hasNonNull(jsonName) ? json.get(jsonName) : null;
    }
}
