package com.example.weather.controller;

import com.example.weather.model.dto.CityDto;
import com.example.weather.model.dto.CityRequest;
import com.example.weather.model.dto.UserDto;
import com.example.weather.model.response.ResponseObject;
import com.example.weather.repository.CityRepository;
import com.example.weather.service.CityService;
import com.example.weather.service.UserService;
import com.example.weather.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final SecurityUtils securityUtils;
    private final CityService cityService;
    private final CityRepository cityRepository;

    @GetMapping("get-user-list")
    public ResponseEntity<ResponseObject> getUsersList(@RequestParam(name = "page", defaultValue = "0") int page,
                                                       @RequestParam(name = "size", defaultValue = "10") int size) {

        ResponseObject response = userService.getUserList(page, size);
        return ResponseEntity.ok(response);
    }

    @PostMapping("add-city")
    public ResponseEntity<ResponseObject> addCity(@RequestBody CityRequest request) {
        if (cityRepository.existsByName(request.getName()))
            return new ResponseEntity<>(new ResponseObject("Ushbu Shahar mavjud"), HttpStatus.BAD_REQUEST);
        CityDto dto = cityService.addCity(securityUtils.getCurrentUser(), request);
        ResponseObject response = new ResponseObject(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("get-user-details/{id}")
    public ResponseEntity<ResponseObject> getUserById(@PathVariable ("id") Long id) {
        ResponseObject response = userService.getUserDetails(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("update")
    public ResponseEntity<ResponseObject> updateUser(@RequestBody UserDto userDto) {
        ResponseObject responseObject = userService.updateUserDetails(securityUtils.getCurrentUser(), userDto);
        return ResponseEntity.ok(responseObject);
    }
}
