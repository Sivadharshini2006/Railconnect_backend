package com.railconnect.trainservice.controller;

import com.railconnect.trainservice.model.Fare;
import com.railconnect.trainservice.repository.FareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fares")
@CrossOrigin(origins = "http://localhost:5173") // Matches your frontend port
public class FareController {

    @Autowired
    private FareRepository repository;

    @GetMapping("/all")
    public ResponseEntity<List<Fare>> getAllFares() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Fare> updateFare(@PathVariable String id, @RequestBody Fare fareDetails) {
        return repository.findById(id).map(fare -> {
            fare.setBaseRate(fareDetails.getBaseRate());
            fare.setPerKm(fareDetails.getPerKm());
            fare.setTatkal(fareDetails.getTatkal());
            fare.setReservation(fareDetails.getReservation());
            return ResponseEntity.ok(repository.save(fare));
        }).orElse(ResponseEntity.notFound().build());
    }
}