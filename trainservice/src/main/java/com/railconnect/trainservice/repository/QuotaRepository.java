package com.railconnect.trainservice.repository;

import com.railconnect.trainservice.model.Quota;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotaRepository extends MongoRepository<Quota, String> {
    // Basic CRUD operations like findAll(), findById(), and save() 
    // are already included by extending MongoRepository.
}