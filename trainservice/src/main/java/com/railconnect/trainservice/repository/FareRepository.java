package com.railconnect.trainservice.repository;

import com.railconnect.trainservice.model.Fare;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FareRepository extends MongoRepository<Fare, String> {
}