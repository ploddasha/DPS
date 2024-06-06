package com.example.demo.controller;
import com.example.demo.model.Airport;
import com.example.demo.model.City;
import com.example.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CitiesController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public List<City> getAllCities(@RequestParam(value = "locale", required = false, defaultValue = "en") String locale) {
        return cityService.getAllCities(locale);
    }

    @GetMapping("/{city}/airports")
    public List<Airport> getAirportsForCity(@PathVariable("city") String city, @RequestParam(value = "locale", required = false, defaultValue = "en") String locale) {
        return cityService.getAirportsForCity(locale, city);
    }

}
