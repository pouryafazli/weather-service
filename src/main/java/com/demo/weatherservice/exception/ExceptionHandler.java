package com.demo.weatherservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<Object> handleCityNotFoundException(CityNotFoundException ex) {
        var weatherServiceException = new WeatherServiceException(
                ex.getMessage(),
                ex.getCause(),
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(weatherServiceException, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ZipcodeNotFoundException.class)
    public ResponseEntity<Object> handleZipcodeNotFoundException(ZipcodeNotFoundException ex) {
        var weatherServiceException = new WeatherServiceException(
                ex.getMessage(),
                ex.getCause(),
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(weatherServiceException, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<Object> handleServiceUnavailableException(ServiceUnavailableException ex) {
        var weatherServiceException = new WeatherServiceException(
                ex.getMessage(),
                ex.getCause(),
                HttpStatus.SERVICE_UNAVAILABLE
        );
        return new ResponseEntity<>(weatherServiceException, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
