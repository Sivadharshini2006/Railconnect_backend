package com.railconnect.trainservice.repository;

import com.railconnect.trainservice.model.Train;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface TrainRepository extends MongoRepository<Train, String> {
    // Find trains by route
    List<Train> findBySourceAndDestination(String source, String destination);
    
    // Check if train exists
    boolean existsByTrainNumber(String trainNumber);
}