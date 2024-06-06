package com.example.demo.repository;

import com.example.demo.model.BoardingPass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckingRepository extends JpaRepository<BoardingPass, Integer> {
    List<BoardingPass> findByBookRef(String bookRef);
}
