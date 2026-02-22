package com.railconnect.trainservice.controller;

import com.railconnect.trainservice.model.Station;
import com.railconnect.trainservice.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
@CrossOrigin(origins = "http://localhost:5173") // Fixes the CORS error from your screenshots
public class StationController {

    @Autowired
    private StationRepository repository;

    @GetMapping("/all")
    public ResponseEntity<List<Station>> getAllStations() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Station> addStation(@RequestBody Station station) {
        return ResponseEntity.ok(repository.save(station));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Station> updateStation(@PathVariable String id, @RequestBody Station stationDetails) {
        return repository.findById(id).map(station -> {
            station.setCode(stationDetails.getCode());
            station.setName(stationDetails.getName());
            station.setCity(stationDetails.getCity());
            station.setState(stationDetails.getState());
            station.setZone(stationDetails.getZone());
            return ResponseEntity.ok(repository.save(station));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStation(@PathVariable String id) {
        repository.deleteById(id);
        return ResponseEntity.ok("Station deleted successfully");
    }
}