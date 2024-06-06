package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Route {

    private String departure_airport;
    private String arrival_airport;

    private List<String> flight_path;

    private String scheduled_departure;
    private String scheduled_arrival;

    @Id
    private String flight_numbers;
    private List<String> departures;
    private List<String> arrivals;
    private Integer price;

    public String getDeparture_airport() {
        return departure_airport;
    }

    public void setDeparture_airport(String departure_airport) {
        this.departure_airport = departure_airport;
    }

    public String getArrival_airport() {
        return arrival_airport;
    }

    public void setArrival_airport(String arrival_airport) {
        this.arrival_airport = arrival_airport;
    }

    public List<String> getFlight_path() {
        return flight_path;
    }

    public void setFlight_path(List<String> flight_path) {
        this.flight_path = flight_path;
    }

    public String getScheduled_departure() {
        return scheduled_departure;
    }

    public void setScheduled_departure(String scheduled_departure) {
        this.scheduled_departure = scheduled_departure;
    }

    public String getScheduled_arrival() {
        return scheduled_arrival;
    }

    public void setScheduled_arrival(String scheduled_arrival) {
        this.scheduled_arrival = scheduled_arrival;
    }

    public String getFlight_numbers() {
        return flight_numbers;
    }

    public void setFlight_numbers(String flight_numbers) {
        this.flight_numbers = flight_numbers;
    }

    public List<String> getDepartures() {
        return departures;
    }

    public void setDepartures(List<String> departures) {
        this.departures = departures;
    }

    public List<String> getArrivals() {
        return arrivals;
    }

    public void setArrivals(List<String> arrivals) {
        this.arrivals = arrivals;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}
