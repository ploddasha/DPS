package com.example.demo.model;

import java.util.List;

public class SelectedRoute {

    private String departureAirport;
    private String arrivalAirport;
    private List<String> flightPath;
    private String scheduledDeparture;
    private String scheduledArrival;
    private List<Integer> flightNumbers;
    private List<String> departures;
    private List<String> arrivals;

    private int price;
    private String fareConditions;
    private String passengerName;
    private String contactData;

    // Getters and setters
    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public List<String> getFlightPath() {
        return flightPath;
    }

    public void setFlightPath(List<String> flightPath) {
        this.flightPath = flightPath;
    }

    public String getScheduledDeparture() {
        return scheduledDeparture;
    }

    public void setScheduledDeparture(String scheduledDeparture) {
        this.scheduledDeparture = scheduledDeparture;
    }

    public String getScheduledArrival() {
        return scheduledArrival;
    }

    public void setScheduledArrival(String scheduledArrival) {
        this.scheduledArrival = scheduledArrival;
    }

    public List<Integer> getFlightNumbers() {
        return flightNumbers;
    }

    public void setFlightNumbers(List<Integer> flightNumbers) {
        this.flightNumbers = flightNumbers;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getFareConditions() {
        return fareConditions;
    }

    public void setFareConditions(String fareConditions) {
        this.fareConditions = fareConditions;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getContactData() {
        return contactData;
    }

    public void setContactData(String contactData) {
        this.contactData = contactData;
    }
}
