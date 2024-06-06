package com.example.demo.repository;

import com.example.demo.model.InboundSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
/*
SELECT DISTINCT flights.flight_no,
      flights.scheduled_arrival as timeOfArrival,
      flights.departure_airport as origin
FROM flights
WHERE flights.arrival_airport = 'AAQ'
GROUP BY flights.flight_no,
         flights.scheduled_arrival,
         flights.departure_airport
ORDER BY flights.flight_no

 */

@Repository
public interface InboundScheduleRepository extends JpaRepository<InboundSchedule, Long> {
    @Query(value = "SELECT DISTINCT flights.flight_no, " +
            "flights.scheduled_arrival, " +
            "flights.departure_airport " +
            "FROM flights " +
            "WHERE flights.arrival_airport = :airportCode " +
            "GROUP BY flights.flight_no, " +
            "flights.scheduled_arrival, " +
            "flights.departure_airport " +
            "ORDER BY flights.flight_no", nativeQuery = true)
    List<InboundSchedule> getInboundSchedules(String airportCode);
}
