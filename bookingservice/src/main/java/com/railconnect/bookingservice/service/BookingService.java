package com.railconnect.bookingservice.service;

import com.railconnect.bookingservice.entity.Booking;
import java.util.List;

public interface BookingService {

    Booking createNewBooking(Booking booking);

    Booking getBookingByPnr(String pnr);

    List<Booking> getBookingsByUser(String email);

    Booking cancelBooking(String pnr);

    void lockBooking(String pnr);

    List<Booking> getActiveTickets(String userEmail);

    Booking confirmBooking(String pnr);
}