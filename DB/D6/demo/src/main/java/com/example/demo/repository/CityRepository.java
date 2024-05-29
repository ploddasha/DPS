package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.model.City;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query()
    List<City> getAllCities(String en);
}
