package com.example.weather.model.entity;

import com.example.weather.model.base.BaseEntity;
import com.example.weather.model.dto.UserDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity {
    private String username;
    private String password;
    private String firstname;
    private String lastname;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<Role> roles = new ArrayList<>();

    private String updateBy;

    public UserDto toUserDto() {
        return new UserDto(getId(), getUsername(), getFirstname(), getLastname());
    }
}