package com.railconnect.trainservice.service;

import com.railconnect.trainservice.model.Train;
import com.railconnect.trainservice.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TrainService {

    @Autowired
    private TrainRepository repository;

    public Train addTrain(Train train) {
        if(repository.existsByTrainNumber(train.getTrainNumber())){
            throw new RuntimeException("Train already exists!");
        }
        return repository.save(train);
    }

    public List<Train> searchTrains(String source, String destination) {
        return repository.findBySourceAndDestination(source, destination);
    }

    public List<Train> getAllTrains() {
        return repository.findAll();
    }
    
    public Train updateTrain(String id, Train updatedTrain) {
        return repository.findById(id).map(train -> {
            train.setTrainName(updatedTrain.getTrainName());
            train.setSource(updatedTrain.getSource());
            train.setDestination(updatedTrain.getDestination());
            train.setTotalSeats(updatedTrain.getTotalSeats());
            train.setRunningDays(updatedTrain.getRunningDays());
            return repository.save(train);
        }).orElseThrow(() -> new RuntimeException("Train not found"));
    }

    public void deleteTrain(String id) {
        repository.deleteById(id);
    }
}