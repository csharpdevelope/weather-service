package com.example.weather.utils;

import com.example.weather.model.entity.User;
import com.example.weather.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SecurityUtils {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        User user = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            Optional<User> optional = userRepository.findByUsername(authentication.getPrincipal().toString());
            if (optional.isPresent()) user = optional.get();
        }
        return user;
    }
}
