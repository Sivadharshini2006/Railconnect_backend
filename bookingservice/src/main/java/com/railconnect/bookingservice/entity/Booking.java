package com.railconnect.bookingservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "bookings")
public class Booking {
    @Id
    private String id;
    private String pnr;
    private String userEmail;
    private String status;
    private LocalDateTime bookingTimestamp; // Add this
    private List<Passenger> passengers;
    

    // Fixes "The method setBookingTimestamp is undefined"
    public void setBookingTimestamp(LocalDateTime bookingTimestamp) {
        this.bookingTimestamp = bookingTimestamp;
    }
    public LocalDateTime getBookingTimestamp() { return bookingTimestamp; }

    // Fixes "The method getPassengers is undefined"
    public List<Passenger> getPassengers() { return passengers; }
    public void setPassengers(List<Passenger> passengers) { this.passengers = passengers; }

    // Standard Getters/Setters for PNR and Status
    public String getPnr() { return pnr; }
    public void setPnr(String pnr) { this.pnr = pnr; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}