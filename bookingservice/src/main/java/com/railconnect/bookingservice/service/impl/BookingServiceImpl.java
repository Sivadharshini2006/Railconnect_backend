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
    private BookingRepository repository;

    @Override
    public List<Booking> getBookingsByUser(String email) {
        // show everything except PAYMENT_PENDING (so user sees BOOKED + CANCELLED)
        return repository.findByUserEmail(email).stream()
                .filter(b -> !"PAYMENT_PENDING".equals(b.getStatus()))
                .collect(Collectors.toList());
    }

    @Override
    public void lockBooking(String pnr) {
        Booking booking = repository.findByPnr(pnr);
        if (booking == null) throw new PnrNotFoundException("PNR " + pnr + " not found.");

        booking.setStatus("CHART_PREPARED");
        repository.save(booking);
    }

    @Override
    public Booking createNewBooking(Booking booking) {
        booking.setPnr(String.valueOf(1000000000L + new Random().nextLong(9000000000L)));
        booking.setStatus("PAYMENT_PENDING");
        booking.setBookingTimestamp(LocalDateTime.now());

        for (Passenger p : booking.getPassengers()) {
            p.setCoach("S" + (new Random().nextInt(10) + 1));
            p.setSeatNumber(new Random().nextInt(72) + 1);
            p.setAssignedBerth(p.getBerthPreference() != null ? p.getBerthPreference() : "LOWER");
            p.setStatus("CONFIRMED");
        }

        return repository.save(booking);
    }

    @Override
    public Booking getBookingByPnr(String pnr) {
        Booking b = repository.findByPnr(pnr);
        if (b == null) throw new PnrNotFoundException("PNR not found");
        return b;
    }

    @Override
    public List<Booking> getActiveTickets(String email) {
        return repository.findByUserEmail(email).stream()
                .filter(b -> "BOOKED".equals(b.getStatus()))
                .collect(Collectors.toList());
    }

    @Override
    public Booking cancelBooking(String pnr) {
        Booking booking = repository.findByPnr(pnr);
        if (booking == null) throw new PnrNotFoundException("PNR not found.");

        if ("CHART_PREPARED".equals(booking.getStatus())) {
            throw new RuntimeException("Chart is prepared. You cannot cancel now.");
        }

        booking.setStatus("CANCELLED");
        return repository.save(booking);
    }

    @Override
    public Booking confirmBooking(String pnr) {
        Booking booking = repository.findByPnr(pnr);
        if (booking == null) throw new PnrNotFoundException("PNR not found.");

        booking.setStatus("BOOKED");
        return repository.save(booking);
    }
}