package com.demo.weatherservice.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class weatherServiceExceptionHandler {

    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<Object> handleCityNotFoundException(CityNotFoundException ex) {
        var weatherServiceException = new WeatherServiceException(
                ex.getMessage(),
                ex.getCause(),
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(weatherServiceException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ZipcodeNotFoundException.class)
    public ResponseEntity<Object> handleZipcodeNotFoundException(ZipcodeNotFoundException ex) {
        var weatherServiceException = new WeatherServiceException(
                ex.getMessage(),
                ex.getCause(),
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(weatherServiceException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<Object> handleServiceUnavailableException(ServiceUnavailableException ex) {
        var weatherServiceException = new WeatherServiceException(
                ex.getMessage(),
                ex.getCause(),
                HttpStatus.SERVICE_UNAVAILABLE
        );
        return new ResponseEntity<>(weatherServiceException, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler({ConstraintViolationException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        var weatherServiceException = new WeatherServiceException(
                ex.getMessage(),
                ex.getCause(),
                HttpStatus.BAD_REQUEST
        );
        return new ResponseEntity<>(weatherServiceException, HttpStatus.BAD_REQUEST);
    }
}
