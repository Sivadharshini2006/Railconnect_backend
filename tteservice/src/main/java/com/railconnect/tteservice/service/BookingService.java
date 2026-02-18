package com.railconnect.tteservice.service;

import com.railconnect.tteservice.entity.Booking;
import java.util.List;

public interface BookingService {
    List<Booking> getActiveTickets(String userEmail); // No curly braces {} here!
}