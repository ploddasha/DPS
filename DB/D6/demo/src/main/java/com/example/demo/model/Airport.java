package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Airport {
    @Id
    private String airportCode;
    private String airportName;
    private String city;
    private String coordinates;
    private String timezone;
}
