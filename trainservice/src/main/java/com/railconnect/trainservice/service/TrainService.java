package com.railconnect.trainservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.railconnect.trainservice.entity.Train;
import com.railconnect.trainservice.repository.TrainRepository;


import java.util.List;

@Service
public class TrainService {

    @Autowired
    private TrainRepository repo;
    @Autowired
    private TrainRepository trainRepository;


    public Train addTrain(Train train) {
        return repo.save(train);
    }

    public List<Train> getAllTrains() {
        return repo.findAll();
    }
    
    public Train updateTrain(String id, Train updatedTrain) {

        Train existingTrain = trainRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Train not found"));

        existingTrain.setTrainNumber(updatedTrain.getTrainNumber());
        existingTrain.setTrainName(updatedTrain.getTrainName());
        existingTrain.setSource(updatedTrain.getSource());
        existingTrain.setDestination(updatedTrain.getDestination());
        existingTrain.setTotalSeats(updatedTrain.getTotalSeats());
        existingTrain.setDepartureTime(updatedTrain.getDepartureTime());
        existingTrain.setArrivalTime(updatedTrain.getArrivalTime());

        return trainRepository.save(existingTrain);
    }
    public void deleteTrain(String id) {
        trainRepository.deleteById(id);
    }

    public List<Train> searchTrains(String source, String destination) {
        return repo.findBySourceAndDestination(source, destination);
    }
}
