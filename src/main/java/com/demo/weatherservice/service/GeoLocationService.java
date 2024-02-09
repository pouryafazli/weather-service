package com.demo.weatherservice.service;

import com.demo.weatherservice.weatheeservice.GeoLocationProvider;
import com.demo.weatherservice.weatheeservice.WeatherProvider;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeoLocationService {

    private final GeoLocationProvider geoLocationProvider;

    public GeoLocationService(GeoLocationProvider geoLocationProvider) {
        this.geoLocationProvider = geoLocationProvider;
    }

    public List<String> getGeoLocation(String city) {

        var response = geoLocationProvider.getGeoLocationValues(city);

        return response;

    }
}
