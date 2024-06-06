package com.example.demo.controller;

import com.example.demo.model.BoardingPass;
import com.example.demo.service.CheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkins")
public class CheckingController {

    @Autowired
    private CheckingService checkingService;

    @GetMapping("/{bookRef}")
    public List<BoardingPass> createChecking(@PathVariable("bookRef") String bookRef) {
        return checkingService.createChecking(bookRef);
    }
}
