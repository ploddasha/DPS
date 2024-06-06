package com.example.demo.service;

import com.example.demo.model.Airport;
import com.example.demo.model.InboundSchedule;
import com.example.demo.model.OutboundSchedule;
import com.example.demo.repository.AirportRepository;
import com.example.demo.repository.InboundScheduleRepository;
import com.example.demo.repository.OutboundScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService {
    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private InboundScheduleRepository inboundScheduleRepository;

    @Autowired
    private OutboundScheduleRepository outboundScheduleRepository;

    public List<Airport> getAllAirports(String locale) {
        return airportRepository.getAllAirports(locale);
    }

    public List<InboundSchedule> getInboundSchedules(String airportCode) {
        return inboundScheduleRepository.getInboundSchedules(airportCode);
    }

    public List<OutboundSchedule> getOutboundSchedules(String airportCode) {
        return outboundScheduleRepository.getOutboundSchedules(airportCode);
    }

}
