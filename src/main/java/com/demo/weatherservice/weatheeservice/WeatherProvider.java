package com.demo.weatherservice.weatheeservice;

import com.demo.weatherservice.response.WeatherResponse;

public interface WeatherProvider {
    WeatherResponse getWeatherByCoordinates(Double latitude, Double longitude);
    WeatherResponse getWeatherByCity(String cityName, String countryCode);
    WeatherResponse getWeatherByZipcode(String zipcode, String countryCode);

}
