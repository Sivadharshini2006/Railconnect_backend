package com.railconnect.trainservice.controller;

import com.railconnect.trainservice.model.Train;
import com.railconnect.trainservice.repository.TrainRepository;
import com.railconnect.trainservice.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/trains")
@CrossOrigin(origins = "http://localhost:5173")
public class TrainController {

    private final TrainService service;
    private final TrainRepository repository;

    // Inject BOTH here to ensure they are resolved
    public TrainController(TrainService service, TrainRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping("/search")
    public List<Train> searchTrains(@RequestParam String source, @RequestParam String destination) {
        // This method is called by your React useEffect
        return repository.findBySourceIgnoreCaseAndDestinationIgnoreCase(source, destination);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Train>> getAll() {
        return ResponseEntity.ok(service.getAllTrains());
    }
    @PostMapping("/add")
    public ResponseEntity<Train> addTrain(@RequestBody Train train) {
        return ResponseEntity.ok(service.addTrain(train));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Train> updateTrain(@PathVariable String id, @RequestBody Train train) {
        return ResponseEntity.ok(service.updateTrain(id, train));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTrain(@PathVariable String id) {
        service.deleteTrain(id); 
        return ResponseEntity.ok("Train with ID " + id + " deleted successfully");
    }

    
  
}
