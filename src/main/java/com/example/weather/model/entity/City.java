package com.example.weather.model.entity;

import com.example.weather.model.base.BaseEntity;
import com.example.weather.model.dto.CityDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cities")
@Getter
@Setter
public class City extends BaseEntity {
    private String name;
    private String latitude;
    private String longitude;
    private String country;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_subscribe_city",
            joinColumns = @JoinColumn(name = "city_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private List<User> users = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "weather_id", referencedColumnName = "id")
    private Weather weather;

    public CityDto toDto() {
        CityDto cityDto = new CityDto();
        cityDto.setId(getId());
        cityDto.setName(getName());
        cityDto.setCountry(getCountry());
        return cityDto;
    }
}
