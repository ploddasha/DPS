package com.example.demo.service;

import com.example.demo.model.City;
import com.example.demo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public List<City> getAllCities(String locale) {
        if (locale == null) {
            return cityRepository.getAllCities("en");
        }
        return cityRepository.getAllCities(locale);
    }
}
