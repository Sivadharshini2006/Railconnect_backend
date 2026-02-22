package com.railconnect.trainservice.repository;

import com.railconnect.trainservice.model.Station;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends MongoRepository<Station, String> {
    // You can add custom search methods here if needed later
}