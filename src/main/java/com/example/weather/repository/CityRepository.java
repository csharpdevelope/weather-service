package com.example.weather.repository;

import com.example.weather.model.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    boolean existsById(Long id);

    boolean existsByName(String name);

    City findByName(String cityName);
}
