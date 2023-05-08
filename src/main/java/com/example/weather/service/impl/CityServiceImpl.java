package com.example.weather.service.impl;

import com.example.weather.model.dto.CityDto;
import com.example.weather.model.dto.CityRequest;
import com.example.weather.model.entity.City;
import com.example.weather.model.entity.User;
import com.example.weather.model.response.ResponseObject;
import com.example.weather.repository.CityRepository;
import com.example.weather.service.CityService;
import com.example.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final CityRepository cityRepository;
    private final WeatherService weatherService;

    @Override
    public ResponseObject getCities() {
        logger.info("Finding All Cities....");
        List<City> list = cityRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        List<CityDto> cities = new ArrayList<>();
        list.forEach(city -> cities.add(city.toDto()));
        logger.info("Finded All Cities");
        return new ResponseObject(cities);
    }

    @Override
    public CityDto addCity(User currentUser, CityRequest request) {
        City city = new City();
        city.setName(request.getName());
        city.setLatitude(request.getLatitude());
        city.setLongitude(request.getLongitude());
        city.setCountry(request.getCountry());
        cityRepository.save(city);
        weatherService.changeWeather(city);
        return city.toDto();
    }
}
