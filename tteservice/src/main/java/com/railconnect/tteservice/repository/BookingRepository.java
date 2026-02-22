package com.railconnect.tteservice.repository;

import com.railconnect.tteservice.entity.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends MongoRepository<Booking, String> {

    Optional<Booking> findByPnr(String pnr);

    // ✅ match Mongo fields
    List<Booking> findByTrainNameAndJourneyDate(String trainName, String journeyDate);
}