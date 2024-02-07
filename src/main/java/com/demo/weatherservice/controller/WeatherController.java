package com.demo.weatherservice.controller;

import com.demo.weatherservice.service.WeatherService;
import com.demo.weatherservice.response.WeatherResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
@Validated
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService){
        this.weatherService = weatherService;
    }

    @GetMapping
    public ResponseEntity<WeatherResponse> getWeatherByCoordinates(@RequestParam @Valid Double lat,
                                                                   @RequestParam @Valid Double lon) {
        var weather = weatherService.getWeatherByCoordinates(lat, lon);
        return ResponseEntity.ok(weather);
    }

    @GetMapping("/city/{city_name}")
    public ResponseEntity<WeatherResponse> getWeatherByCity(@PathVariable("city_name") @NotBlank String cityName,
                                                            @RequestParam(required = false) String countryCode) {
        var weather = weatherService.getWeatherByCity(cityName, countryCode);
        return ResponseEntity.ok(weather);
    }

    @GetMapping("/zipcode/{zipcode}")
    public ResponseEntity<WeatherResponse> getWeatherByZipcode(@PathVariable("zipcode") @NotBlank String zipcode,
                                                               @RequestParam(required = false) String countryCode) {
        var weather = weatherService.getWeatherByZipcode(zipcode, countryCode);
        return ResponseEntity.ok(weather);
    }

}
