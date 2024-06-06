package com.example.demo.repository;

import com.example.demo.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.model.City;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query(value = "SELECT airports_data.city ->> :locale as city, " +
            "airports_data.timezone " +
            "FROM airports_data", nativeQuery = true)
    List<City> getAllCities(String locale);

}
