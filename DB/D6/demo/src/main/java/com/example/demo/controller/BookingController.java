package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.model.SelectedRoute;
import com.example.demo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public HttpEntity<BookingService.BookingResponse> createBooking(@RequestBody SelectedRoute selectedRoute) {
        return bookingService.createBooking(selectedRoute);
    }

}
