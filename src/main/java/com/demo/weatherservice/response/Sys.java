package com.demo.weatherservice.response;


public record Sys(Integer type, Integer id, String country, Long sunrise, Long sunset) {
}
