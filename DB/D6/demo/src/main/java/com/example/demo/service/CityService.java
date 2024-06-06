package com.example.demo.service;

import com.example.demo.model.Airport;
import com.example.demo.model.City;
import com.example.demo.repository.AirportRepository;
import com.example.demo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private AirportRepository airportRepository;

    public List<City> getAllCities(String locale) {
        return cityRepository.getAllCities(locale);
    }

    public List<Airport> getAirportsForCity(String locale, String city) {
        return airportRepository.getAirportsForCity(locale, city);
    }
}
