package com.railconnect.bookingservice.repository;

import com.railconnect.bookingservice.entity.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {
    Booking findByPnr(String pnr);
    java.util.List<Booking> findByUserEmail(String userEmail);
}