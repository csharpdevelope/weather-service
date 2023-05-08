package com.example.weather.exception;

import com.example.weather.model.response.ErrorResponse;
import com.example.weather.model.response.ResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseObject> notFoundException(NotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimestamp(new Date());
        ResponseObject response = new ResponseObject(404, "Not Found", errorResponse);
        return ResponseEntity.status(404).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseObject> exception(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimestamp(new Date());
        ResponseObject response = new ResponseObject(500,"Error", errorResponse);
        return ResponseEntity.internalServerError().body(response);
    }
}
