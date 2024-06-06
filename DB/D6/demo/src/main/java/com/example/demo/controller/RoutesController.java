package com.example.demo.controller;

import com.example.demo.model.Route;
import com.example.demo.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/routes")
public class RoutesController {
    @Autowired
    private RouteService routeService;

    @GetMapping
    public List<Route> getRoutes(
            @RequestParam("departureDate") String departureDate,
            @RequestParam("departurePoint") String departurePoint,
            @RequestParam("arrivalPoint") String arrivalPoint,
            @RequestParam(value = "bookingClass", required = false, defaultValue = "Economy") String bookingClass,
            @RequestParam(value = "limit", required = false, defaultValue = "0") int limit) {
        return routeService.getRoutes(departureDate, departurePoint, arrivalPoint, bookingClass, limit);
    }
}
