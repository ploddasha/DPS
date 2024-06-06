package com.example.demo.repository;

import com.example.demo.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    @Query(value = "WITH RECURSIVE routes AS ( " +
            "    SELECT " +
            "        f.flight_no, " +
            "        f.departure_airport, " +
            "        f.arrival_airport, " +
            "        ARRAY[]::VARCHAR[] AS flight_path, " +
            "        ARRAY[f.scheduled_departure] AS scheduled_departure, " +
            "        f.scheduled_arrival, " +
            "        ARRAY[f.flight_id] AS flight_numbers, " +
            "        ARRAY[f.departure_airport::bpchar] AS departures, " +
            "        ARRAY[f.arrival_airport::bpchar] AS arrivals, " +
            "        ad.city ->> 'en' AS arrival_city, " +
            "        (SELECT COALESCE(MIN(fp.avg_price), 0) FROM flight_prices fp WHERE fp.flight_no = f.flight_no AND fp.fare_conditions = 'Economy') AS price, " +
            "        0 AS connections " +
            "    FROM " +
            "        bookings.flights f " +
            "    JOIN " +
            "        airports_data ad ON f.arrival_airport = ad.airport_code " +
            "    WHERE " +
            "        (f.departure_airport = 'DME' OR ad.city ->> 'en' = 'DME') " +
            "        AND DATE(f.scheduled_departure) = TO_DATE('2017-07-16', 'YYYY-MM-DD') " +
            " " +
            "    UNION ALL " +
            " " +
            "    SELECT " +
            "        r.flight_no, " +
            "        r.departure_airport, " +
            "        f.arrival_airport, " +
            "        r.flight_path || (ad2.city ->> 'en')::VARCHAR, " +
            "        r.scheduled_departure || f.scheduled_departure, " +
            "        f.scheduled_arrival, " +
            "        r.flight_numbers || f.flight_id, " +
            "        r.departures || f.departure_airport, " +
            "        r.arrivals || f.arrival_airport, " +
            "        ad.city ->> 'en', " +
            "        r.price + (SELECT MIN(fp.avg_price) FROM flight_prices fp WHERE fp.flight_no = f.flight_no AND fp.fare_conditions = 'Economy'), " +
            "        r.connections + 1 " +
            "    FROM " +
            "        bookings.flights f " +
            "    JOIN " +
            "        airports_data ad ON f.arrival_airport = ad.airport_code " +
            "    JOIN " +
            "        airports_data ad2 ON f.departure_airport = ad2.airport_code " +
            "    JOIN " +
            "        routes r ON ad2.city ->> 'en' = r.arrival_city " +
            "    WHERE " +
            "        f.scheduled_departure > r.scheduled_arrival " +
            "        AND f.scheduled_departure - r.scheduled_arrival < INTERVAL '1 day' " +
            "        AND r.connections < 1 " +
            ") " +
            "SELECT " +
            "    r.departure_airport, " +
            "    r.arrival_airport, " +
            "    r.flight_path, " +
            "    r.scheduled_departure, " +
            "    r.scheduled_arrival, " +
            "    r.flight_numbers, " +
            "    r.departures, " +
            "    r.arrivals, " +
            "    r.price " +
            "FROM " +
            "    routes r " +
            "JOIN " +
            "    airports_data ad ON r.arrival_airport = ad.airport_code " +
            "WHERE " +
            "    r.arrival_airport = 'LED' OR ad.city ->> 'en' = 'LED' " +
            "ORDER BY " +
            "    r.connections, r.price; ",
            nativeQuery = true)
    List<Route> findRoutes(String departureDate,
                           String departurePoint,
                           String arrivalPoint,
                           String bookingClass,
                           int limit);
}

