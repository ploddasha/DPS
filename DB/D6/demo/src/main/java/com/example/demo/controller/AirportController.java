package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/airports")
public class AirportController {
    @Autowired
    private AirportService airportService;

    @GetMapping
    public List<Airport> getAllAirports(@RequestParam(value = "locale", required = false, defaultValue = "en") String locale) {
        return airportService.getAllAirports(locale);
    }

    @GetMapping("/{airport}/schedules/inbound")
    public List<InboundScheduleWithDays> getInboundSchedules(@PathVariable("airport") String airport) {
        List<InboundSchedule> list = airportService.getInboundSchedules(airport);
        Map<String, InboundScheduleWithDays> scheduleMap = new HashMap<>();

        for (InboundSchedule inboundSchedule : list) {
            String key = inboundSchedule.getFlight_no() + inboundSchedule.getScheduled_arrival();
            InboundScheduleWithDays schedule = scheduleMap.get(key);

            if (schedule == null) {
                schedule = new InboundScheduleWithDays(inboundSchedule.getFlight_no(),
                        inboundSchedule.getScheduled_arrival(), inboundSchedule.getDeparture_airport());
                scheduleMap.put(key, schedule);
            }

            schedule.calculateDaysOfWeek(inboundSchedule.getScheduled_arrival());
        }

        return new ArrayList<>(scheduleMap.values());
    }

    @GetMapping("/{airport}/schedules/outbound")
    public List<OutboundScheduleWithDays> getOutboundSchedules(@PathVariable("airport") String airport) {
        List<OutboundSchedule> list = airportService.getOutboundSchedules(airport);
        Map<String, OutboundScheduleWithDays> scheduleMap = new HashMap<>();

        for (OutboundSchedule outboundSchedule : list) {
            String key = outboundSchedule.getFlight_no() + outboundSchedule.getScheduled_departure();
            OutboundScheduleWithDays schedule = scheduleMap.get(key);

            if (schedule == null) {
                schedule = new OutboundScheduleWithDays(outboundSchedule.getFlight_no(),
                        outboundSchedule.getScheduled_departure(), outboundSchedule.getArrival_airport());
                scheduleMap.put(key, schedule);
            }

            schedule.calculateDaysOfWeek(outboundSchedule.getScheduled_departure());
        }

        return new ArrayList<>(scheduleMap.values());
    }

}
