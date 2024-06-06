package com.example.demo.service;

import com.example.demo.model.Booking;
import com.example.demo.model.SelectedRoute;
import com.example.demo.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public ResponseEntity<BookingResponse> createBooking(SelectedRoute selectedRoute) {
        Random random = new Random();
        int bookRef = 100000 + random.nextInt(900000);

        Booking booking = new Booking();
        booking.setBookRef(String.valueOf(bookRef));

        booking.setDepartureAirport(selectedRoute.getDepartureAirport());
        booking.setArrivalAirport(selectedRoute.getArrivalAirport());
        booking.setFlightPath(selectedRoute.getFlightPath());
        booking.setScheduledDeparture(selectedRoute.getScheduledDeparture());
        booking.setScheduledArrival(selectedRoute.getScheduledArrival());
        booking.setFlightNumbers(selectedRoute.getFlightNumbers());
        booking.setDepartures(selectedRoute.getDepartures());
        booking.setArrivals(selectedRoute.getArrivals());
        booking.setPrice(selectedRoute.getPrice());
        booking.setFareConditions(selectedRoute.getFareConditions());
        booking.setPassengerName(selectedRoute.getPassengerName());
        booking.setContactData(selectedRoute.getContactData());

        bookingRepository.save(booking);
        return ResponseEntity.ok().body(new BookingResponse(bookRef));

        //return bookingRepository.save(booking);
    }

    static class BookingResponse {
        private int bookRef;

        public BookingResponse(int bookRef) {
            this.bookRef = bookRef;
        }

        public int getBookRef() {
            return bookRef;
        }

        public void setBookRef(int bookRef) {
            this.bookRef = bookRef;
        }
    }

}
