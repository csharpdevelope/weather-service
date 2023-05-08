package com.example.weather.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityRequest {
    @NotNull
    private String name;
    private String latitude;
    private String longitude;
    @NotNull
    private String country;
}
