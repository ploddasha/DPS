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
    public List<City> getAllCities(@RequestParam("locale") String locale) {
        return cityService.getAllCities(locale);
    }

    @GetMapping("/{city}/airports")
    public List<Airport> airports(@PathVariable("city") String city) {
        return null;
    }

}
