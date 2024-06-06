package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class InboundScheduleWithDays {
    @Id
    private String flight_no;
    private String scheduled_arrival;
    private String departure_airport;

    private Set<Integer> daysOfWeek = new HashSet<>();

    public InboundScheduleWithDays(String flight_no, String scheduled_arrival, String departure_airport) {
        this.flight_no = flight_no;
        this.scheduled_arrival = scheduled_arrival;
        this.departure_airport = departure_airport;
    }

    public Set<Integer> getDaysOfWeek() {
        return daysOfWeek;
    }


    public void calculateDaysOfWeek(String scheduled_arrival) {
        if (scheduled_arrival != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX");
            OffsetDateTime offsetDateTime = OffsetDateTime.parse(scheduled_arrival, formatter);
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

    public String getScheduled_arrival() {
        return scheduled_arrival;
    }

    public void setScheduled_arrival(String scheduled_arrival) {
        this.scheduled_arrival = scheduled_arrival;
    }

    public String getDeparture_airport() {
        return departure_airport;
    }

    public void setDeparture_airport(String departure_airport) {
        this.departure_airport = departure_airport;
    }
}
