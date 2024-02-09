package com.demo.weatherservice.weatheeservice;

import java.util.List;

public interface GeoLocationProvider {

    List<String> getGeoLocationValues(String city);
}
