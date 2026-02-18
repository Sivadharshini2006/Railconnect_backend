package com.railconnect.tteservice.repository;

import com.railconnect.tteservice.entity.Chart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChartRepository extends MongoRepository<Chart, String> {
}
