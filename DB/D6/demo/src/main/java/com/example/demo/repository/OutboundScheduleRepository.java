package com.example.demo.repository;

import com.example.demo.model.OutboundSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutboundScheduleRepository extends JpaRepository<OutboundSchedule, Long> {

    @Query(value = "SELECT DISTINCT flights.flight_no, " +
            "flights.scheduled_departure, " +
            "flights.arrival_airport " +
            "FROM flights " +
            "WHERE flights.departure_airport = :airportCode " +
            "GROUP BY flights.flight_no, " +
            "flights.scheduled_departure, " +
            "flights.arrival_airport " +
            "ORDER BY flights.flight_no", nativeQuery = true)
    List<OutboundSchedule> getOutboundSchedules(String airportCode);
}
