package com.railconnect.bookingservice.service.impl;

import com.railconnect.bookingservice.entity.Booking;
import com.railconnect.bookingservice.entity.Passenger;
import com.railconnect.bookingservice.repository.BookingRepository;
import com.railconnect.bookingservice.service.BookingService;
import com.railconnect.bookingservice.utils.PnrNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository repository; // Use this consistently

    @Override
    public List<Booking> getMyBookings(String email) {
        // Fetch all and filter to HIDE "CHART_PREPARED" trains from passengers
        return repository.findByUserEmail(email).stream()
                .filter(booking -> !"CHART_PREPARED".equals(booking.getStatus()))
                .collect(Collectors.toList());
    }

    @Override
    public void lockBooking(String pnr) {
        Booking booking = repository.findByPnr(pnr);
        if (booking != null) {
            booking.setStatus("CHART_PREPARED");
            repository.save(booking);
        } else {
            throw new PnrNotFoundException("PNR " + pnr + " not found.");
        }
    }

    @Override
    public Booking createNewBooking(Booking booking) {
        booking.setPnr(String.valueOf(1000000000L + new Random().nextLong(9000000000L)));
        booking.setStatus("BOOKED");
        booking.setBookingTimestamp(LocalDateTime.now());
        
        for (Passenger p : booking.getPassengers()) {
            p.setCoach("S" + (new Random().nextInt(10) + 1));
            p.setSeatNumber(new Random().nextInt(72) + 1);
            p.setAssignedBerth(p.getBerthPreference() != null ? p.getBerthPreference() : "LOWER");
            
            // ADD THIS: Set the status for the passenger so it's not null!
            p.setStatus("CONFIRMED"); 
        }
        
        return repository.save(booking);
    }

    @Override
    public Booking getBookingByPnr(String pnr) {
        Booking booking = repository.findByPnr(pnr);
        
        // This logic triggers the GlobalExceptionHandler
        if (booking == null) {
            throw new PnrNotFoundException("PNR not found with ID: " + pnr);
        }
        
        return booking;
    }
    @Override
    public List<Booking> getBookingsByUser(String email) {
        return repository.findByUserEmail(email);
    }
    @Override
    public List<Booking> getActiveTickets(String email) {
        // Put your body logic here!
        return repository.findByUserEmail(email).stream()
               .filter(b -> !"CHART_PREPARED".equals(b.getStatus()))
               .collect(Collectors.toList());
    }
    

    @Override
    public Booking cancelBooking(String pnr) {
        Booking booking = repository.findByPnr(pnr);
        
        if (booking == null) {
            throw new PnrNotFoundException("PNR not found.");
        }

        // This is the logic that hides the 'Cancel' option 
        if ("CHART_PREPARED".equals(booking.getStatus())) {
            throw new RuntimeException("Chart is prepared. You cannot cancel or view modification details now.");
        }

        booking.setStatus("CANCELLED");
        return repository.save(booking);
    }
}