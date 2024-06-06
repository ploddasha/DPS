package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;


@Entity
public class OutboundScheduleWithDays {
    @Id
    private String flight_no;
    private String scheduled_departure;
    private String arrival_airport;

    private final Set<Integer> daysOfWeek = new HashSet<>();

    public OutboundScheduleWithDays(String flight_no, String scheduled_departure, String arrival_airport) {
        this.flight_no = flight_no;
        this.scheduled_departure = scheduled_departure;
        this.arrival_airport = arrival_airport;
    }

    public Set<Integer> getDaysOfWeek() {
        return daysOfWeek;
    }


    public void calculateDaysOfWeek(String scheduled_departure) {
        if (scheduled_departure != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX");
            OffsetDateTime offsetDateTime = OffsetDateTime.parse(scheduled_departure, formatter);
            DayOfWeek dayOfWeek = offsetDateTime.getDayOfWeek();
            daysOfWeek.add(dayOfWeek.getValue());
        }
    }

    public String getFlight_no() {
        return flight_no;
    }

    public void setFlight_no(String flight_no) {
        this.flight_no = flight_no;
    }

    public String getScheduled_departure() {
        return scheduled_departure;
    }

    public void setScheduled_departure(String scheduled_departure) {
        this.scheduled_departure = scheduled_departure;
    }

    public String getArrival_airport() {
        return arrival_airport;
    }

    public void setArrival_airport(String arrival_airport) {
        this.arrival_airport = arrival_airport;
    }
}

