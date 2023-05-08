package com.example.weather.controller;

import com.example.weather.model.dto.LoginRequest;
import com.example.weather.model.dto.RegisterRequest;
import com.example.weather.model.dto.SubscribeDto;
import com.example.weather.model.response.ResponseObject;
import com.example.weather.service.CityService;
import com.example.weather.service.UserService;
import com.example.weather.utils.SecurityUtils;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CityService cityService;
    private final SecurityUtils securityUtils;

    @PostMapping("register")
    public ResponseEntity<ResponseObject> registerUser(@RequestBody RegisterRequest request) {
        ResponseObject object = userService.registerUser(request);
        return new ResponseEntity<>(object, HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<ResponseObject> loginUser(@RequestBody LoginRequest request) {
        ResponseObject response = userService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("subscribe-to-city")
    public ResponseEntity<ResponseObject> subscribeToCity(@RequestBody SubscribeDto subscribe) {
        ResponseObject response = userService.subscribeToCity(securityUtils.getCurrentUser(), subscribe);
        return ResponseEntity.ok(response);
    }

    @RolesAllowed(value = "ADMIN")
    @GetMapping("get-city-list")
    public ResponseEntity<ResponseObject> getCitiesList() {
        ResponseObject response = cityService.getCities();
        return ResponseEntity.ok(response);
    }

    @GetMapping("get-subscriptions")
    public ResponseEntity<ResponseObject> getSubscriptions() {
        ResponseObject response = userService.getSubscriptionsCity(securityUtils.getCurrentUser());
        return ResponseEntity.ok(response);
    }
}
