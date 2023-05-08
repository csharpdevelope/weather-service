package com.example.weather.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityDto {
    private Long id;
    private String name;
    private String country;
}
