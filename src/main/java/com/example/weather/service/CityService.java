package com.example.weather.service;

import com.example.weather.model.dto.CityDto;
import com.example.weather.model.dto.CityRequest;
import com.example.weather.model.entity.User;
import com.example.weather.model.response.ResponseObject;

public interface CityService {
    ResponseObject getCities();

    CityDto addCity(User currentUser, CityRequest request);
}
