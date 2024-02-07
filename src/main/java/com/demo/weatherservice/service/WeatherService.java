package com.demo.weatherservice.service;

import com.demo.weatherservice.exception.ServiceUnavailableException;
import com.demo.weatherservice.response.WeatherResponse;
import com.demo.weatherservice.weatheeservice.WeatherProvider;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);
    private final WeatherProvider weatherProvider;
    public WeatherService(WeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
    }

    /**
     * Retrieves the current weather for the specified coordinates.
     * This method fetches the weather information for the given latitude and longitude coordinates
     * using the weather provider API.
     *
     * @param latitude  the latitude of the location for which to retrieve the weather
     * @param longitude the longitude of the location for which to retrieve the weather
     * @return the current weather information for the specified coordinates
     */
    public WeatherResponse getWeatherByCoordinates(Double latitude, Double longitude) {
        logger.info("Getting current weather for coordinates: latitude={}, longitude={}", latitude, longitude);
        var response = weatherProvider.getWeatherByCoordinates(latitude, longitude);
        logger.info("Current weather found for coordinates {}, {}: {}", latitude, longitude, response);
        return response;
    }

    /**
     * Retrieves the current weather for the specified city.
     * This method fetches the weather information for the given city name and country code
     * using the weather provider API.
     *
     * @param cityName    the name of the city for which to retrieve the weather
     * @param countryCode the ISO 3166 country code of the city
     * @return the current weather information for the specified city
     */
    @CircuitBreaker(name = "CIRCUIT_BREAKER", fallbackMethod = "fallback")
    public WeatherResponse getWeatherByCity(String cityName, String countryCode) {
        logger.info("Getting current weather for city: {}", cityName);
        var response = weatherProvider.getWeatherByCity(cityName, countryCode);
        logger.info("Current weather found for city {}: {}", cityName, response);
        return response;
    }

    /**
     * Retrieves the current weather for the specified zipcode.
     * This method fetches the weather information for the given zipcode and country code
     * using the weather provider API.
     *
     * @param zipcode     the zipcode for which to retrieve the weather
     * @param countryCode the ISO 3166 country code of the location
     * @return the current weather information for the specified zipcode
     */
    @CircuitBreaker(name = "CIRCUIT_BREAKER", fallbackMethod = "fallback")
    public WeatherResponse getWeatherByZipcode(String zipcode, String countryCode) {
        logger.info("Getting current weather for zipcode: {}", zipcode);
        var response = weatherProvider.getWeatherByZipcode(zipcode, countryCode);
        logger.info("Current weather found for zipcode {}: {}", zipcode, response);
        return response;
    }

    /**
     * Fallback method called when the primary weather service is unavailable.
     * This method logs the error and handles the fallback strategy.
     *
     * <p>Depending on the business requirements, there are various strategies
     * that can be applied when the primary weather service is unavailable. This
     * may include fetching the current weather from an alternative provider or
     * returning cached data to provide the latest available information instead
     * of throwing an exception.
     *
     * @param ex the exception indicating the primary weather service is unavailable
     * @return a {@code WeatherResponse} object representing the current weather,
     *         either fetched from an alternative provider or retrieved from cache
     * @throws ServiceUnavailableException if the fallback strategy also fails
     */
    public WeatherResponse fallback(ServiceUnavailableException ex) {
        logger.error("Fallback method called for getWeather", ex);
        // Implement fallback strategy here
        // For example, fetch weather from an alternative provider or return cached data
        throw new ServiceUnavailableException("Current weather service is not available right now");
    }

}
