package com.demo.weatherservice.service;

import com.demo.weatherservice.weatheeservice.GeoLocationProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class GeoLocationServiceTest {

    @Mock
    GeoLocationProvider geoLocationProvider;
    @InjectMocks

    GeoLocationService geoLocationService;


    @Test
    public void getGetGeoLocation() {
        var city = "Montreal";

        when(geoLocationProvider.getGeoLocationValues(city)).thenReturn(List.of("1","2"));
        var response = geoLocationService.getGeoLocation(city);
        assertNotNull(response);
        assertEquals(2, response.size());
        assertEquals("1", response.get(0));
        assertEquals("2", response.get(1));

        var city1 = new City("Montreal","Canada");
        var city2 = new City("Toronto","Canada");
        var city4 = new City("AMontreal","Canada");


        var city3 = new City("Montreal","US");

        List<City> cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);
        cities.add(city3);
        cities.add(city4);

        cities.sort(new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        Map<String,List<City>> map = cities.stream().collect(Collectors.groupingBy(c ->c.country));
        System.out.print(map);
        map.values().stream().sorted().collect(Collectors.toList());
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    public class City{
        String name;
        String country;
    }


}