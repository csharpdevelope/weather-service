package com.example.weather.service.impl;

import com.example.weather.exception.NotFoundException;
import com.example.weather.model.dto.*;
import com.example.weather.model.entity.City;
import com.example.weather.model.entity.Role;
import com.example.weather.model.entity.User;
import com.example.weather.model.response.ResponseObject;
import com.example.weather.model.response.UserPagination;
import com.example.weather.repository.CityRepository;
import com.example.weather.repository.RoleRepository;
import com.example.weather.repository.UserRepository;
import com.example.weather.service.JwtService;
import com.example.weather.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CityRepository cityRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final ObjectMapper objectMapper;

    private final int MAX_CITY_ADD = 10;

    @Override
    public ResponseObject getUserList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userRepository.findAll(pageable);
        if (userPage.getContent().size() == 0) {
            return new ResponseObject("Data not available");
        }
        UserPagination pagination = new UserPagination();
        pagination.setTotalElements(userPage.getTotalElements());
        pagination.setTotalPages(userPage.getTotalPages());
        pagination.setSize(userPage.getSize());
        pagination.setNumberOfElements(userPage.getNumberOfElements());
        List<UserDto> userDtos = new ArrayList<>();
        userPage.getContent()
                .stream()
                .map(user -> userDtos.add(user.toUserDto()));

        pagination.setContent(userDtos);
        return new ResponseObject(pagination);
    }

    @Override
    public ResponseObject getUserDetails(Long id) {
        User user = getUserById(id);
        return new ResponseObject(user.toUserDto());
    }

    @Override
    public ResponseObject updateUserDetails(User currentUser, UserDto userDto) {
        User user = getUserById(userDto.getId());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setUsername(userDto.getUsername());
        user.setUpdateBy(currentUser.getUsername());
        userRepository.save(user);
        return new ResponseObject("User data has been updated");
    }

    @Override
    public ResponseObject registerUser(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return new ResponseObject("This username available");
        }
        User user = new User();
        user.setActive(true);
        user.setUsername(request.getUsername());
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Role role = roleRepository.findByName(Role.RoleType.ROLE_USER);
        user.setRoles(List.of(role));
        userRepository.save(user);
        logger.info("User Created");
        return new ResponseObject("User created");
    }

    @Override
    public ResponseObject login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username or password invalid"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            logger.error("Password error");
            throw new UsernameNotFoundException("Username or password invalid");
        }
        ObjectNode data = objectMapper.createObjectNode();
        data.put("id", user.getId());
        data.put("username", user.getUsername());
        data.put("firstname", user.getFirstname());
        data.put("lastname", user.getLastname());
        data.put("token", jwtService.generateToken(user));
        return new ResponseObject(data);
    }

    @Override
    public ResponseObject subscribeToCity(User currentUser, SubscribeDto subscribe) {
        if (!cityRepository.existsById(subscribe.getCityId())) {
            return new ResponseObject("No City exists");
        }
        List<City> cities = getUserCities(currentUser);
        if (cities.size() <= MAX_CITY_ADD) {
            cities.add(cityRepository.findById(subscribe.getCityId()).get());
            cityRepository.saveAll(cities);
            return new ResponseObject("City added successfully.");
        } else {
            return new ResponseObject("You can only subscribe to 10 cities");
        }
    }

    @Override
    public ResponseObject getSubscriptionsCity(User currentUser) {
        List<CityDto> responseCities = new ArrayList<>();
        List<City> cities = getUserCities(currentUser);
        if (cities.isEmpty()) {
            return new ResponseObject("There are no cities for this user");
        }
        for (City city : getUserCities(currentUser)) {
            responseCities.add(city.toDto());
        }
        return new ResponseObject(responseCities);
    }

    private List<City> getUserCities(User currentUser) {
        return new ArrayList<>();
    }

    private User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }
}
