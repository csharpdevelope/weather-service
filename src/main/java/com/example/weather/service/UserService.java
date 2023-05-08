package com.example.weather.service;

import com.example.weather.model.dto.LoginRequest;
import com.example.weather.model.dto.RegisterRequest;
import com.example.weather.model.dto.SubscribeDto;
import com.example.weather.model.dto.UserDto;
import com.example.weather.model.entity.User;
import com.example.weather.model.response.ResponseObject;

public interface UserService {
    ResponseObject getUserList(int page, int size);

    ResponseObject getUserDetails(Long id);

    ResponseObject updateUserDetails(User currentUser, UserDto userDto);

    ResponseObject registerUser(RegisterRequest request);

    ResponseObject login(LoginRequest request);

    ResponseObject subscribeToCity(User currentUser, SubscribeDto subscribe);

    ResponseObject getSubscriptionsCity(User currentUser);
}
