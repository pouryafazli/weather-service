package com.demo.weatherservice.service;

import com.demo.weatherservice.response.Clouds;
import com.demo.weatherservice.response.Coord;
import com.demo.weatherservice.response.Main;
import com.demo.weatherservice.response.Sys;
import com.demo.weatherservice.response.Weather;
import com.demo.weatherservice.response.WeatherResponse;
import com.demo.weatherservice.response.Wind;
import com.demo.weatherservice.weatheeservice.WeatherProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

    @Mock
    private WeatherProvider weatherProvider;

    @InjectMocks
    private WeatherService weatherService;

    @Test
    void testGetCurrentWeatherByCity() {
        var cityName = "London";
        var expectedResponse = generateWeatherResponse();
        when(weatherProvider.getWeatherByCity(cityName, null)).thenReturn(expectedResponse);
        var actualResponse = weatherService.getWeatherByCity(cityName, null);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testGetCurrentWeatherByCityAndCountry() {
        var cityName = "London";
        var country = "uk";
        var expectedResponse = generateWeatherResponse();
        when(weatherProvider.getWeatherByCity(cityName, country)).thenReturn(expectedResponse);
        var actualResponse = weatherService.getWeatherByCity(cityName, country);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testGetCurrentWeatherByZipcode() {
        var zipcode = "94040";
        var expectedResponse = generateWeatherResponse();
        when(weatherProvider.getWeatherByZipcode(zipcode, null)).thenReturn(expectedResponse);
        var actualResponse = weatherService.getWeatherByZipcode(zipcode, null);
        assertEquals(expectedResponse, actualResponse);
    }

    private WeatherResponse generateWeatherResponse() {
        return new WeatherResponse(
                new Coord(10.99, 44.34),
                List.of(new Weather(804, "Clouds", "overcast clouds", "04d")),
                "stations",
                new Main(283.23, 282.07, 278.64, 284.86, 1014, 68, 1014, null),
                10000,
                new Wind(0.94, 197, 2.6),
                new Clouds(97),
                1707303824L,
                new Sys(2, 2004688, "IT", 1707287281L, 1707323538L),
                3600,
                3163858,
                "Zocca",
                200
        );
    }


}
