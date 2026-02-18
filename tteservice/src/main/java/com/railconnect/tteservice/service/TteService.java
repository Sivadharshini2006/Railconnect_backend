package com.railconnect.tteservice.service;

import com.railconnect.tteservice.entity.*;
import com.railconnect.tteservice.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TteService {
    private final BookingRepository bookingRepository;
    private final ChartRepository chartRepository;

    public TteService(BookingRepository bookingRepository, ChartRepository chartRepository) {
        this.bookingRepository = bookingRepository;
        this.chartRepository = chartRepository;
    }

    // Fixes "undefined getWaitlist" error
    public List<Booking> getWaitlist(String trainId, String date) {
        List<Booking> bookings = bookingRepository.findByTrainIdAndDate(trainId, date);
        bookings.forEach(b -> {
            // b.getPassengers() will now work after manual generation!
            if (b.getPassengers() != null) {
                b.getPassengers().removeIf(p -> p.getStatus() == null || !p.getStatus().startsWith("WL"));
            }
        });
        return bookings;
    }
    // Fixes "undefined allotSeat" error
    public void allotSeat(String pnr) {
        Booking booking = bookingRepository.findByPnr(pnr)
                .orElseThrow(() -> new RuntimeException("PNR not found"));
        
        // booking.getPassengers() will now work!
        booking.getPassengers().forEach(p -> {
            if (p.getStatus() != null && p.getStatus().startsWith("WL")) {
                p.setStatus("CONFIRMED");
            }
        });
        bookingRepository.save(booking);
    }

    // Fixes "undefined generateChart" error
    public Chart generateChart(String trainId, String date) {
        List<Booking> bookings = bookingRepository.findByTrainIdAndDate(trainId, date);
        Chart chart = new Chart();
        chart.setTrainId(trainId);
        chart.setDate(date);
        chart.setBookings(bookings);
        return chartRepository.save(chart);
    }

    // Fixes "undefined verifyTicket" error
    public Booking verifyTicket(String pnr) {
        return bookingRepository.findByPnr(pnr)
                .orElseThrow(() -> new RuntimeException("PNR not found"));
    }
}