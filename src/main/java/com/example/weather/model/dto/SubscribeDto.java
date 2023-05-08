package com.example.weather.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscribeDto {
    @JsonProperty("city_id")
    private Long cityId;
}
