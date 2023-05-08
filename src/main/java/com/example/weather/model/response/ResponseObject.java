package com.example.weather.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseObject {
    private int code;
    private String message;
    private Object data;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private ErrorResponse error;

    public ResponseObject(Object data) {
        this.message = "success";
        this.code = 200;
        this.data = data;
    }

    public ResponseObject(int code, String message, ErrorResponse error) {
        this.code = code;
        this.message = message;
        this.error = error;
    }
}
