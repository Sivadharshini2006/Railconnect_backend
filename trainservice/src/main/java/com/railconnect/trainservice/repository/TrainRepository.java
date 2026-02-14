package com.railconnect.trainservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.railconnect.trainservice.entity.Train;

import java.util.List;

public interface TrainRepository extends MongoRepository<Train, String> {

    List<Train> findBySourceAndDestination(String source, String destination);
}
