package com.example.weather.model.entity;

import com.example.weather.model.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "request_log")
@Getter
@Setter
public class RequestLog extends BaseEntity {
    private String request;
    private String response;
    private String path;
    private Integer code;
    private long duration;
}
