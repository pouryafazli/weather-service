package com.demo.weatherservice.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class WeatherConfig {
    @Value("${weather.api.key}")
    private String apiKey;

}

