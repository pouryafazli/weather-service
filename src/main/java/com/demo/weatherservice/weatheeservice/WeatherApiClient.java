package com.demo.weatherservice.weatheeservice;

import com.demo.weatherservice.response.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "weather-api", url = "https://api.openweathermap.org")
@Component
public interface WeatherApiClient {
    @GetMapping("/data/2.5/weather")
    WeatherResponse getWeatherByCoordinates(@RequestParam("lat") Double latitude,
                                            @RequestParam("lon") Double longitude,
                                            @RequestParam("appid") String apiKey);

    @GetMapping("/data/2.5/weather")
    WeatherResponse getWeatherByCityName(@RequestParam("q") String cityName,
                               @RequestParam("appid") String apiKey);

    @GetMapping("/data/2.5/weather")
    WeatherResponse getWeatherByZipCode(@RequestParam("zip") String zipCode,
                               @RequestParam("appid") String apiKey);
}
