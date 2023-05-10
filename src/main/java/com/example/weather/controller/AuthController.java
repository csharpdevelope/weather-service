package com.example.weather.controller;

import com.example.weather.model.dto.LoginRequest;
import com.example.weather.model.dto.RegisterRequest;
import com.example.weather.model.response.ResponseObject;
import com.example.weather.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
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
}
