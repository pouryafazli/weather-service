package com.demo.weatherservice.weatheeservice;

import com.demo.weatherservice.config.WeatherConfig;
import com.demo.weatherservice.exception.CityNotFoundException;
import com.demo.weatherservice.exception.ZipcodeNotFoundException;
import com.demo.weatherservice.response.WeatherResponse;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class OpenWeatherMapProvider implements WeatherProvider{

    private static final Logger logger = LoggerFactory.getLogger(OpenWeatherMapProvider.class);

    private final WeatherApiClient weatherApiClient;
    private final WeatherConfig weatherConfig;

    public OpenWeatherMapProvider(WeatherApiClient weatherApiClient,
                          WeatherConfig weatherConfig) {
        this.weatherApiClient = weatherApiClient;
        this.weatherConfig = weatherConfig;
    }
    @Override
    public WeatherResponse getWeatherByCoordinates(Double latitude, Double longitude) {
        logger.info("Getting weather by coordinates: latitude={}, longitude={}", latitude, longitude);
        var response = weatherApiClient.getWeatherByCoordinates(latitude, longitude, weatherConfig.getApiKey());
        logger.info("Weather found for coordinates {}{}: {}", latitude, longitude, response);
        return response;
    }

    @Override
    public WeatherResponse getWeatherByCity(String cityName, String countryCode) {
        if (!Objects.isNull(countryCode) && !countryCode.isBlank()) {
            cityName = cityName + "," + countryCode;
        }
        logger.info("Getting weather by city: {}", cityName);
        try {
            var response = weatherApiClient.getWeatherByCityName(cityName, weatherConfig.getApiKey());
            logger.info("Weather found for city {}: {}", cityName, response);
            return response;
        } catch (FeignException.NotFound e) {
            logger.warn("City not found: {}", cityName);
            throw new CityNotFoundException(String.format("City %s not found", cityName), e.getCause());
        }
    }

    @Override
    public WeatherResponse getWeatherByZipcode(String zipcode, String countryCode) {
        if (!Objects.isNull(countryCode) && !countryCode.isBlank()) {
            zipcode = zipcode + "," + countryCode;
        }
        logger.info("Getting weather by zipcode: {}", zipcode);

        try {
            WeatherResponse response = weatherApiClient.getWeatherByZipCode(zipcode, weatherConfig.getApiKey());
            logger.info("Weather found for zipcode {}: {}", zipcode, response);
            return response;
        } catch (FeignException.NotFound e) {
            logger.warn("Zipcode not found: {}", zipcode);
            throw new ZipcodeNotFoundException(String.format("Zipcode %s not found", zipcode), e.getCause());
        }
    }
}
