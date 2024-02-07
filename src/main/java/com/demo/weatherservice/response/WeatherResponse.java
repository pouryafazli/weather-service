package com.demo.weatherservice.response;

import java.util.List;

public record WeatherResponse(
        Coord coord,
        List<Weather> weather,
        String base,
        Main main,
        Integer visibility,
        Wind wind,
        Clouds clouds,
        Long dt,
        Sys sys,
        Integer timezone,
        Integer id,
        String name,
        Integer cod
) {
}


