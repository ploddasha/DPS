package com.example.demo.repository;

import com.example.demo.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
    @Query(value = "SELECT airports_data.airport_code, " +
            "airports_data.airport_name ->> :locale as airport_name, " +
            "airports_data.city ->> :locale as city, " +
            "airports_data.coordinates, " +
            "airports_data.timezone " +
            "FROM airports_data " +
            "WHERE airports_data.city ->> :locale = :city",
            nativeQuery = true)
    List<Airport> getAirportsForCity(String locale, String city);

    @Query(value = "SELECT airports_data.airport_code, " +
            "airports_data.airport_name ->> :locale as airport_name, " +
            "airports_data.city ->> :locale as city, " +
            "airports_data.coordinates, " +
            "airports_data.timezone " +
            "FROM airports_data", nativeQuery = true)
    List<Airport> getAllAirports(String locale);
}
