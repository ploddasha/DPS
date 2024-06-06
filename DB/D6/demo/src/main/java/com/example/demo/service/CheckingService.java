package com.example.demo.service;

import com.example.demo.model.BoardingPass;
import com.example.demo.repository.CheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckingService {

    @Autowired
    private CheckingRepository checkingRepository;

    public List<BoardingPass> createChecking(String bookRef) {

        List<BoardingPass> boardingPasses = checkingRepository.findByBookRef(bookRef);

        if (boardingPasses.isEmpty()) {
            BoardingPass boardingPass = new BoardingPass();
            boardingPass.setTicketNo(generateTicketNumber());
            boardingPass.setFlightId("FL123");
            boardingPass.setBoardingNo(1);
            boardingPass.setSeatNo("12A");
            boardingPass.setPassengerName("John Doe");
            boardingPass.setBookRef(bookRef);

            // Save the new boarding pass to the repository
            boardingPasses.add(checkingRepository.save(boardingPass));
        }

        return boardingPasses;
    }

    private int generateTicketNumber() {
        // Implement logic to generate a unique ticket number
        return (int) (Math.random() * 1000000);
    }
}
