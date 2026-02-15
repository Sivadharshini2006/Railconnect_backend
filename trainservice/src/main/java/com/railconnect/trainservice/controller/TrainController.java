package com.railconnect.trainservice.controller;

import com.railconnect.trainservice.model.Train;
import com.railconnect.trainservice.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/trains")
public class TrainController {

    @Autowired
    private TrainService service;

    // Postman: GET http://localhost:8082/api/trains/all
    @GetMapping("/all")
    public ResponseEntity<List<Train>> getAll() {
        return ResponseEntity.ok(service.getAllTrains());
    }
    @PostMapping("/add")
    public ResponseEntity<Train> addTrain(@RequestBody Train train) {
        return ResponseEntity.ok(service.addTrain(train));
    }

    // Postman: POST http://localhost:8082/api/trains/update/1
    @PostMapping("/update/{id}") // NOTE: This is POST, not PUT
    public ResponseEntity<Train> updateTrain(@PathVariable String id, @RequestBody Train train) {
        return ResponseEntity.ok(service.updateTrain(id, train));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTrain(@PathVariable String id) {
        service.deleteTrain(id); // This calls the delete logic in your Service
        return ResponseEntity.ok("Train with ID " + id + " deleted successfully");
    }

    // Postman: GET http://localhost:8082/api/trains/search?source=Chennai&destination=Delhi
    @GetMapping("/search")
    public ResponseEntity<List<Train>> search(@RequestParam String source, @RequestParam String destination) {
        return ResponseEntity.ok(service.searchTrains(source, destination));
    }
}
