package com.example.weather.model.response;

import com.example.weather.model.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserPagination {
    private List<UserDto> content;

    private Long totalElements;

    private Integer totalPages;

    private Integer size;

    private Integer numberOfElements;
}
