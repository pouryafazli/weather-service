package com.demo.weatherservice.exception;

import org.springframework.http.HttpStatus;


public record WeatherServiceException(String message, Throwable throwable, HttpStatus httpStatus) { }
