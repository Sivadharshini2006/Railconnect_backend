package com.railconnect.tteservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "bookings")
public class Booking {

    @Id
    private String id;

    private String pnr;
    private String status;
    private String bookingTimestamp;

    private String trainName;     // ✅ exists in Mongo
    private String journeyDate;   // ✅ exists in Mongo (NOT "date")

    private List<Passenger> passengers;

    // getters setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPnr() { return pnr; }
    public void setPnr(String pnr) { this.pnr = pnr; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getBookingTimestamp() { return bookingTimestamp; }
    public void setBookingTimestamp(String bookingTimestamp) { this.bookingTimestamp = bookingTimestamp; }

    public String getTrainName() { return trainName; }
    public void setTrainName(String trainName) { this.trainName = trainName; }

    public String getJourneyDate() { return journeyDate; }
    public void setJourneyDate(String journeyDate) { this.journeyDate = journeyDate; }

    public List<Passenger> getPassengers() { return passengers; }
    public void setPassengers(List<Passenger> passengers) { this.passengers = passengers; }
}