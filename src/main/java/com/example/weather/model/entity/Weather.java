package com.example.weather.model.entity;

import com.example.weather.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "weathers")
@Getter
@Setter
public class Weather extends BaseEntity {
    @Column(name = "city_name")
    private String country;
    private String countryCode;
    private String temperature;
    @Column(name = "now_date")
    private String currentDate;
    private String weatherMain;
}
