package com.railconnect.trainservice.service;

import com.railconnect.trainservice.model.Train;
import com.railconnect.trainservice.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {

    @Autowired
    private TrainRepository repository;

    // ✅ Add Train (same trainNumber allowed, but variantId must be unique)
    public Train addTrain(Train train) {

        // default variantId if not sent
        if (train.getVariantId() == null || train.getVariantId().trim().isEmpty()) {
            train.setVariantId("A");
        }

        // ✅ prevent duplicate (trainNumber + variantId)
        if (repository.existsByTrainNumberAndVariantId(train.getTrainNumber(), train.getVariantId())) {
            throw new RuntimeException("Train schedule already exists for this trainNumber + variantId!");
        }

        return repository.save(train);
    }

    public List<Train> searchTrains(String source, String destination) {
        return repository.findBySourceIgnoreCaseAndDestinationIgnoreCase(source, destination);
    }

    public List<Train> getAllTrains() {
        return repository.findAll();
    }

    // ✅ Update all fields (so React gets full data)
    public Train updateTrain(String id, Train updatedTrain) {
        return repository.findById(id).map(train -> {

            // Basic info
            train.setTrainNumber(updatedTrain.getTrainNumber());
            train.setTrainName(updatedTrain.getTrainName());
            train.setSource(updatedTrain.getSource());
            train.setDestination(updatedTrain.getDestination());

            // Timings
            train.setDepTime(updatedTrain.getDepTime());
            train.setArrTime(updatedTrain.getArrTime());
            train.setDuration(updatedTrain.getDuration());

            // Flags
            train.setAC(updatedTrain.isAC());              // if your setter is setIsAC, tell me
            train.setHasTatkal(updatedTrain.isHasTatkal()); // if getter is isHasTatkal() then OK

            // Seats & price
            train.setTotalSeats(updatedTrain.getTotalSeats());
           

            
            train.setClasses(updatedTrain.getClasses());
            train.setRunningDays(updatedTrain.getRunningDays());

            // variantId
            if (updatedTrain.getVariantId() != null && !updatedTrain.getVariantId().trim().isEmpty()) {
                train.setVariantId(updatedTrain.getVariantId());
            }

            return repository.save(train);

        }).orElseThrow(() -> new RuntimeException("Train not found"));
    }

    public void deleteTrain(String id) {
        repository.deleteById(id);
    }
}